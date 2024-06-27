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

Responsive design aims to improve the usability of your app on different devices and varying window sizes. Responsive web design is by many considered to be a task dedicated to CSS magicians. With Vaadin the same end-result can also be accomplished in pure Java. And in fact, you can accomplish much more than what is possible with plain CSS.

With an actual programming language, like Java or TypeScript, you can not just adjust some details of the layout like with CSS, but you can even change the whole component/element to be more suitable for your user. Or for example adjust the amount of data sent to the browser.

Let’s check five examples on how to improve the UX on different screen sizes, in pure Java. 

If you want to contribute your example or just play hands on with this example, [check it out from
GitHub](https://github.com/mstahv/rwdpj).

                """));
    }
}
