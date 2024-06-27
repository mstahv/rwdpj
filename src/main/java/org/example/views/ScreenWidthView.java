package org.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.util.ResizeObserver;

@Route(layout = DefaultLayout.class)
@MenuItem(order = 999, icon = VaadinIcon.TABLET)
public class ScreenWidthView extends VerticalLayout {

    public ScreenWidthView() {
        add(new RichText().withMarkDown("""
                # Changing the layout based device properties
                
                At most trivial level, responsive design in your Vaadin application can be just selecting components
                appropriate for the device and use case. There are a number of different mechanisms to implmement
                this logic, from sniffing user agent (generally not considered the best practice) to reading various
                variables from the browser.
                
                As a trivial example of this, this view uses "extended client details", from the browser and then 
                based on the browser window width, renders "cards" either horizontally or vertically. Although the
                size is the most commonly evaluated property, you shouldn't lock yourself only to it. Similarly 
                sized tablet might benefit from quite different UI patterns than a similar desktop browser.
                
                If you want to check how the app behaves on a smartphone (with a desktop computer),
                resize the browser window and reload.
                Although people rarely resize their browser windows on desktop (unless testing responsive design),
                dynamically adjusted UI may be important for example when rotating a mobile devices.
                In the next step you'll learn how to dynamically adapt the UI, if that is needed.
                """));

        var cards = new AvailableWidthView.Card[]{new AvailableWidthView.Card("DIV1"), new AvailableWidthView.Card("DIV2"), new AvailableWidthView.Card("DIV3"), new AvailableWidthView.Card("DIV4")};
        UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
            if (details.getBodyClientWidth() < 800) {
                add(new VerticalLayout(cards));
            } else {
                add(new HorizontalLayout(cards));
            }
        });

    }

    public static class Card extends Div {
        public Card(String text) {
            super(text);
            setMinWidth("100px");
            getStyle().setBackground("pink");
            getStyle().setPadding("1em");
        }
    }
}
