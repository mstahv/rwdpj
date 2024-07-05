package org.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.CodeSnippet;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;

@Route(layout = DefaultLayout.class)
@MenuItem(order = 999, icon = VaadinIcon.TABLET)
public class ScreenWidthView extends VerticalLayout {

    public ScreenWidthView() {
        add(new H1("Changing the layout based device properties"));

        add(new CodeSnippet(getClass(), 44, 51));

        add(new RichText().withMarkDown("""
                At the most trivial level, responsive design in your Vaadin application can be just selecting 
                components appropriate for the device and use case. There are a number of different mechanisms 
                to implement this logic, from sniffing the user agent (generally not considered the best 
                practice) to reading various variables from the browser.

                As a trivial example of this, this view uses "extended client details" from the browser and 
                then, based on the browser window width, renders "cards" either horizontally or vertically. 
                Although size is the most commonly evaluated property, you shouldn't lock yourself only to 
                it. A large-sized tablet might benefit from quite different UI patterns than a similarly 
                sized desktop browser.

                If you want to check how the app behaves on a smartphone (with a desktop computer), 
                **shrink the browser window and reload**. Although in real life people rarely resize their 
                browser windows on desktop (unless testing responsive design), a dynamically adjusted UI may 
                be important, for example, when rotating mobile devices.

                In the next step, you'll learn how to dynamically adapt the UI if that is needed.
                                """));

        var cards = new Card[]{new Card("DIV1"), new Card("DIV2"), new Card("DIV3"), new Card("DIV4")};
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
