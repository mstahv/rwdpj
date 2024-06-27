package org.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;

@Route(layout = DefaultLayout.class)
@MenuItem(icon = VaadinIcon.QUOTE_LEFT)
public class BuiltInComponentsView extends VerticalLayout {

    public BuiltInComponentsView() {
        add(new RichText().withMarkDown("""
        # Use built-in components
        
        Vaadin components provide certain "responsive features" out of the box, when appropriate.
        Sometimes that is all what is needed for a decent UX.
        
        A grand example is the AppLayout, used by parent layout in this example, will adapt based on the
        screen size.
        
        Also, for example the Select component behaves slightly differently than on desktop device, 
        if used on a smartphones.
        """));

        Select<String> select = new Select<>();
        select.setItems("Foo", "Bar", "Baz");
        select.setValue("Foo");
        add(select);
    }
}
