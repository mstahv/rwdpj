
package org.vaadin.rwdpj;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.svenjacobs.loremipsum.LoremIpsum;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
public class ShopItem extends VerticalLayout {

    static int position;
    
    static final LoremIpsum li = new LoremIpsum();

    static ShopItem[] get(int i) {
        ShopItem[] items = new ShopItem[i];
        for (int j = 0; j < 10; j++) {
            items[j] = new ShopItem();
        }
        return items;
    }
    
    public ShopItem() {
        setMargin(true);
        setSpacing(true);
        Label l = new Label(li.getParagraphs(1));
        l.setCaption(li.getWords(2, getPage()));
        addComponent(l);
        final Button button = new Button("Request special offer");
        button.addStyleName(ValoTheme.BUTTON_LINK);
        addComponent(new HorizontalLayout(new Button("Buy"), button));
        
    }

    protected static int getPage() {
        return position++%50;
    }

}
