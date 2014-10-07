package org.vaadin.rwdpj;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SuppressWarnings("serial")
@Title("Responsive Web Design with Plain Java :-)")
@Push
public class ResponsiveVaadinUI extends UI {

    private final static int MENU_WIDTH = 200;
    private final static int MIN_SLOT_WIDTH = 300;

    public enum LayoutMode {

        SMALL, DESKTOP
    }

    // fields used for "dynamically adjusting layout"
    private LayoutMode currentMode;
    private int currentColumnCount;

    private final String[] optionCaptions = new String[]{"Option", "Another", "Third"};
    private final Resource[] optionIcons = new Resource[]{FontAwesome.ANCHOR, FontAwesome.ANDROID, FontAwesome.APPLE};

    @Override
    protected void init(VaadinRequest request) {
        layout();

        // Normally "dynamically adjusting layout" is not needed, but nice
        // for demonstrating RWD 
        currentMode = getLayoutMode();
        currentColumnCount = getOptimalColumnCount();
        getPage().addBrowserWindowResizeListener(e -> {
            if (currentMode != getLayoutMode()
                    || currentColumnCount != getOptimalColumnCount()) {
                currentMode = getLayoutMode();
                currentColumnCount = getOptimalColumnCount();
                // rebuild layout if necessary
                layout();
            }
        });

        // Configure resize events to be "lazy"
        setResizeLazy(true);

    }

    private void layout() {
        Component menu = layoutMenu();
        VerticalLayout layout = getMainLayout();

        if (getLayoutMode() == LayoutMode.SMALL) {
            // If small screen devices, add menu as first component in vertical 
            // main layout.
            layout.addComponentAsFirst(menu);
            setContent(layout);
        } else {
            // For larger screens, place menu on the left
            HorizontalLayout horizontalLayout = new HorizontalLayout(menu,
                    layout);
            horizontalLayout.setWidth("100%");
            horizontalLayout.setExpandRatio(layout, 1);
            setContent(horizontalLayout);

        }

    }

    private VerticalLayout getMainLayout() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        GridLayout gridLayout = new GridLayout(getOptimalColumnCount(), 1);
        gridLayout.setSizeFull();
        gridLayout.addComponents(ShopItem.get(getSaneAmoutOfItems()));
        layout.addComponent(gridLayout);

        return layout;
    }

    /**
     * Example method how you can adjust the amount of displayed data according
     * to end users device. This method has just some hard coded values per
     * "mode", but the amount could of course be calculated dynamically as well.
     * This kind of intelligent UI code can make loading faster and rendering
     * snappier for mobile devices.
     *
     * @return the sane amount of items to be displayed at once
     */
    private int getSaneAmoutOfItems() {
        if (getLayoutMode() == LayoutMode.SMALL) {
            return 5;
        } else {
            return 10;
        }
    }

    private int getOptimalColumnCount() {
        return getMainAreaWidth() / MIN_SLOT_WIDTH;
    }

    private int getMainAreaWidth() {
        int availableWidth = Page.getCurrent().getBrowserWindowWidth();
        if (getLayoutMode() == LayoutMode.DESKTOP) {
            // in desktop mode menu is on the left side of main content area
            availableWidth -= MENU_WIDTH;
        }
        return availableWidth;
    }

    /**
     * An example method that selects from two different menu implementations
     * based on the current layout mode.
     *
     * @return a menu suitable for current layout mode
     */
    private Component layoutMenu() {
        if (getLayoutMode() == LayoutMode.SMALL) {
            // "hamburger icon" and submenu
            NativeSelect select = new NativeSelect();
            select.setWidth("100%");
            select.addValueChangeListener(e -> {
                Notification.show("Demo effect!");
            });
            for (int i = 0; i < optionCaptions.length; i++) {
                select.addItem(optionCaptions[i]);
            }
            return select;
        }
        Button[] buttons = new Button[optionCaptions.length];
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i]
                    = new Button(optionCaptions[i], optionIcons[i]);
            button.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
            button.addClickListener(e -> {
                Notification.show("Demo effect!");
            });
        }
        final VerticalLayout menulayout = new VerticalLayout(buttons);
        menulayout.setWidth(MENU_WIDTH, Unit.PIXELS);
        menulayout.setMargin(true);
        return menulayout;

    }

    /**
     * @return the "main mode" suitable for the client screen size. This example
     * only has two modes, but there can naturally be as many modes as one
     * wants. And the mode can also be determined on other details but width as
     * well.
     */
    private LayoutMode getLayoutMode() {
        // As an example, some other variables that the layout mode might depend
        String browserApplication = Page.getCurrent().getWebBrowser().
                getBrowserApplication();
        boolean touchDevice = Page.getCurrent().getWebBrowser().isTouchDevice();

        int width = Page.getCurrent().getBrowserWindowWidth();
        if (width < 800) {
            return LayoutMode.SMALL;
        } else {
            return LayoutMode.DESKTOP;
        }
    }

}
