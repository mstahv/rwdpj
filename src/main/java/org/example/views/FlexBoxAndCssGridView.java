package org.example.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.CodeSnippet;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;

import java.util.stream.IntStream;

@Route(layout = DefaultLayout.class)
@MenuItem(icon = VaadinIcon.COMPILE)
public class FlexBoxAndCssGridView extends VerticalLayout {

    public FlexBoxAndCssGridView() {
        add(new RichText().withMarkDown("""
                # Utilizing Flexbox and CSS Grid layouts instead of breakpoints

                With the help of modern browser layout techniques, you can often accomplish good results 
                for different browser sizes without explicit breakpoints, whether they are implemented 
                with or without Java.

                Flexbox and CSS [Grid layouts](https://vaadin.com/blog/five-methods-to-create-grid-layouts-with-vaadin-flow) 
                are naturally available for Java developers as well, but we 
                are missing a ton of examples (and probably some helper components) to make their usage 
                easier. I'm periodically teasing our very own Rolf Smeds (who sits on the roadmap of our 
                Design System team), slightly tongue in cheek, that *we could solve 80% of the responsive 
                needs of our customers by introducing a component called HorizontalLayoutThatWraps* 🤣

                Well, Rolf has been buried under other requests, so I present to you: 
                **the HorizontalLayoutThatWraps**. Implemented based on the FlexLayout component, with a 
                single meaningful configuration line (*setFlexWrap(FlexWrap.WRAP);*) that makes the flexbox 
                row wrap the components when they no longer fit in the available horizontal space. Although 
                quite trivial, this is a good example of how you can efficiently utilize the available 
                screen estate completely without breakpoint-based logic.
                """));

        add(new CodeSnippet(getClass(), 52, 56));

        add(new RichText().withMarkDown("""
                With Flexbox and Css Grid there is plenty of other possibilities, but naturally the rather "extreme
                flexibility" can also make things rather complex and decrease the maintainability. I suggest to package
                your "recipes" with Flexbox and Css Grid into classes, both for improved readability and re-usability.
                And help me to remind Rolf periodically that we (Java developers) would really benefit of a couple of
                well named (and designed) presets for these complex layout components 🧸
                """));

        class HorizontalLayoutThatWraps extends FlexLayout {
            public HorizontalLayoutThatWraps() {
                setFlexWrap(FlexWrap.WRAP);
            }
        }

        var horizontalLayoutThatWraps = new HorizontalLayoutThatWraps();
        IntStream.rangeClosed(1, 15).forEach(i ->
                horizontalLayoutThatWraps.add(new Card("Card" + i))
        );
        add(horizontalLayoutThatWraps);
    }

    public static class Card extends Div {
        public Card(String text) {
            super(text);
            setMinWidth("100px");
            getStyle().setBackground("pink");
            getStyle().setPadding("1em");
            getStyle().setMargin("1em");
        }
    }
}
