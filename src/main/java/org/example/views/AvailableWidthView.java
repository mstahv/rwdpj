package org.example.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import org.example.CodeSnippet;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.util.ResizeObserver;

@Route(layout = DefaultLayout.class)
@MenuItem(icon = VaadinIcon.CROP)
public class AvailableWidthView extends VerticalLayout {

    public AvailableWidthView() {
        add(new H1("Responding to available width"));
        add(new CodeSnippet(getClass(), 63, 71));
        add(new RichText().withMarkDown("""
                In the previous example, we used a quite trivial mechanism to make decisions based on the 
                screen size. Most commonly in CSS, these decisions are made with a feature called media 
                queries, which allow you to target specific browser window sizes (and certain other 
                properties). Recently (around 2023), major browsers gained support for the related 
                Containment Module, so now CSS "breakpoints" can also be hooked to the actual available 
                width of a specific element. These approaches are naturally available for Vaadin development 
                as well, if you master CSS.
                                                       
                Programmatically, in Java (or on the browser side with TypeScript), you can do exactly 
                the same by listening to window resize events or observing the size of individual components. 
                The only difference is that the code is much more readable for Java developers. Not only 
                are you limited to adjusting the CSS, but you can also change the component structure or 
                configure components differently (see the Grid column example!).
                                                       
                In the following example, we are observing the width of content in the second slot of a 
                SplitLayout. If the size is less than 600px, we render content horizontally; otherwise, we 
                render it vertically. We also adjust to possible changes in slot size. The available size can 
                be changed by various user interactions like window resizing, rotating a mobile device, 
                opening/collapsing the main menu, or by dragging the split bar in the component.
                                                       
                Although the same could probably be accomplished by BrowserWindowResizeListener in the Page 
                or with traditional CSS media queries, the logic easily becomes very complex that way. 
                Additionally, you would need to fetch the initial size using the retrieveExtendedClientDetails() 
                method from the Page.
                                                       
                Thus, we use a helper class called [ResizeObserver](https://vaadin.com/directory/component/flow-viritin) 
                built around the similarly named JS API. The helper is currently available in the Viritin add-on, 
                but I hope we can get a similar helper in the core for the next minor release. The Java API has 
                (configurable) debouncing for the events, so it doesn't choke the client-server communication if 
                the browser drops you an event on each and every pixel change.
                                """));

        Div left = new Div("Left");
        Div right = new Div("Right");
        var split = new SplitLayout(left, right);
        split.setWidthFull();
        split.setMinHeight("300px");

        var cards = new Card[]{new Card("DIV1"), new Card("DIV2"), new Card("DIV3"), new Card("DIV4")};
        ResizeObserver.get().observe(right, size -> {
            right.removeAll();
            if (size.width() < 600) {
                right.add(new VerticalLayout(cards));
            } else {
                right.add(new HorizontalLayout(cards));
            }
        });

        add(split);

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
