package org.vaadin.rwdpj;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("coolthemewithoutresponsivelogic")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public enum LayoutMode {

        SMALL, DESKTOP
    }

    private LayoutMode currentMode;

    @Override
    protected void init(VaadinRequest request) {
        layout();

        getPage().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {

                    @Override
                    public void browserWindowResized(
                            Page.BrowserWindowResizeEvent event) {
                                if (currentMode != getLayoutMode()) {
                                    currentMode = getLayoutMode();
                                    // rebuild layout if necessary
                                    layout();
                                }
                            }
                });
    }

    private void layout() {
        Component menu = layoutMenu();
        VerticalLayout layout = getMainLayout();

        if (getLayoutMode() == LayoutMode.SMALL) {
            layout.addComponentAsFirst(menu);
            setContent(layout);
        } else {
            HorizontalLayout horizontalLayout = new HorizontalLayout(menu,
                    layout);
            horizontalLayout.setWidth("100%");
            horizontalLayout.setExpandRatio(layout, 1);
            setContent(horizontalLayout);

        }

    }

    protected VerticalLayout getMainLayout() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        Label title = new Label("<h1>RWD in plain Java</h1>", ContentMode.HTML);

        layout.
                addComponents(title,
                        new Label("TODO add 'fluent grid' example'"));
        return layout;
    }

    private final String[] optionCaptions = new String[]{"Option", "Another", "Third"};
    private final Resource[] optionIcons = new Resource[]{FontAwesome.ANCHOR, FontAwesome.ANDROID, FontAwesome.APPLE};

    private Component layoutMenu() {
        if (getLayoutMode() == LayoutMode.SMALL) {
            // "hamburger icon" and submenu
            MenuBar menu = new MenuBar();
            menu.setWidth("100%");
            MenuItem root = menu.addItem("", FontAwesome.BARS, null);
            for (int i = 0; i < optionCaptions.length; i++) {
                root.addItem(optionCaptions[i], optionIcons[i],
                        new MenuBar.Command() {
                            @Override
                            public void menuSelected(MenuItem selectedItem) {
                                Notification.show("Demo effect!");
                            }
                        });
            }
            return menu;
        }
        Button[] buttons = new Button[optionCaptions.length];
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i] = 
                    new Button(optionCaptions[i], optionIcons[i]);
            button.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    Notification.show("Demo effect!");
                }
            });
        }
        final VerticalLayout menulayout = new VerticalLayout(buttons);
        menulayout.setWidthUndefined();
        return menulayout;

    }

    public LayoutMode getLayoutMode() {
        int width = Page.getCurrent().getBrowserWindowWidth();
        if (width < 800) {
            return LayoutMode.SMALL;
        } else {
            return LayoutMode.DESKTOP;
        }
    }

}
