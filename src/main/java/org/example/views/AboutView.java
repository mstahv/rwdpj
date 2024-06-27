package org.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.DefaultLayout;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;

@Route(value = "", layout = DefaultLayout.class)
@MenuItem(title = "About this demo", icon = VaadinIcon.QUOTE_LEFT, order = MenuItem.BEGINNING)
public class AboutView extends VerticalLayout {

    public AboutView() {
        add(new RichText().withMarkDown("""
# Responsive Web Design in Pure Java

This is a demo project showcasing various methods how Vaadin developers can enhance their apps UX with varying devices 
and screen sizes, with pure Java skills.

If you want to contribute your example, please make a PR to the 
[GitHub project](https://github.com/mstahv/rwdpj).

                """));
        Button button = new Button("Click me",
                event -> add(new Paragraph("Clicked!")));
        add(button);
    }
}
