package org.example.views;

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
@MenuItem(icon = VaadinIcon.QUOTE_LEFT)
public class AvailableWidthView extends VerticalLayout {

    public static class Card extends Div {
        public Card(String text) {
            super(text);
            setMinWidth("100px");
            getStyle().setBackground("pink");
            getStyle().setPadding("1em");
        }
    }

    public AvailableWidthView() {
        add(new RichText().withMarkDown("""
                # Responding to available width

                In the previous example we used quite trivial mechanism to do decisions based on the screen size.
                Most commonly in CSS these are done with a feature called media queries, that allows you to target
                selected browser window sizes (and certain other properties).
                Recently ( ~ 2023) major browsers gained support for related Containment Module, so now CSS "break 
                points" can be hooked also to the actually available width of a specific element. 
                These approaches are naturally available for Vaadin development as well, in case you master CSS...

                Programmatically, in Java (or in browser side with TypeScript) you can do exactly the same by listening 
                to window resize events or observing size of individual components. The only difference is that the code
                is way more readable for Java developers and you are limited to adjusting the CSS, you can also change 
                the component structure or configure components differently (see the Grid column example!).

                In the following example we are observing the width of a content in the seconds slot of a SplitLayout.
                If the size is less than 600px, we render content horizontally, otherwise vertically. And we also adjust
                to possible changes in slot size.
                The available size can be changed by various user interactions like the window resizing, rotating a 
                mobile device, opening/collapsing the main menu or by dragging the splitbar in the component. 
                
                Although the same probably be accomplished by BrowserWindowResizeListener in the Page or with 
                traditional Css media queres, the logic easily becomes very complex that way. Also you'd also need to 
                fetch the initial size using retrieveExtendedClientDetails() method from the Page. 
                 
                Thus, I'm instead using helper class called ResizeObserver built around the similarly named
                JS API. The helper is currently available in the Viritin add-on, but I hope we can get similar helper to
                the core for the next minor release. The Java API has (configurable) debouncing for the events, so it 
                doesn't choke the client-server communication if the browser drops you an event on each and every pixel
                change.

                """));

        Div left = new Div("Left");
        Div right = new Div("Right");
        var split = new SplitLayout(left, right);
        split.setWidthFull();
        split.setMinHeight("300px");

        var cards = new Card[]{new Card("DIV1"), new Card("DIV2"), new Card("DIV3"), new Card("DIV4")};
        ResizeObserver.get().observe(right, size -> {
            right.removeAll();
            right.add(new Paragraph("Right slot width now: " + size.width()));
            if(size.width() < 600) {
                right.add(new VerticalLayout(cards));
            } else {
                right.add(new HorizontalLayout(cards));
            }
        });

        add(split);

    }
}
