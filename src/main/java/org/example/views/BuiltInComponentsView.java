package org.example.views;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;

@Route(layout = DefaultLayout.class)
@MenuItem(icon = VaadinIcon.COMBOBOX)
public class BuiltInComponentsView extends VerticalLayout {

    public BuiltInComponentsView() {
        add(new RichText().withMarkDown("""
        # Use built-in components
        
        Vaadin components are designed to be used on varying devices. They provide certain "responsive features" out of the box. Sometimes, that is all that is needed for a decent UX.
                        
        A great example is the [AppLayout](https://vaadin.com/docs/latest/components/app-layout), used as the parent layout in this example, which adapts based on the screen size.
                                         
        Additionally, for example, the Select component behaves slightly differently on a smartphone compared to a desktop device.
        """));

        Select<String> select = new Select<>();
        select.setItems("Foo", "Bar", "Baz");
        select.setValue("Foo");
        add(select);
    }
}
