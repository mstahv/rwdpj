package org.example.views;

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

Responsive design aims to improve the usability of your app on different devices and varying window sizes. Responsive web design is considered by many to be a task dedicated to CSS magicians. With Vaadin, the same end result can also be accomplished in pure Java. In fact, you can achieve much more than what is possible with plain CSS.

With an actual programming language, like Java or TypeScript, you can not only adjust some details of the layout as you would with CSS, but you can also change entire components or elements to be more suitable for your user. For example, you can adjust the amount of data sent to the browser.

Let’s check out five examples of how to improve the UX on different screen sizes using pure Java.
                                                                                               
To see the full source of the example view, use the link in top right corner. If you want to contribute your example or just try this hands-on with IDE [check it out from
GitHub](https://github.com/mstahv/rwdpj).

                """));
    }
}
