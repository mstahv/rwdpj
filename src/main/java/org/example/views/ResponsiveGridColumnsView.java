package org.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.DefaultLayout;
import org.example.data.DataService;
import org.example.data.domain.Person;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.components.grid.VGrid;
import org.vaadin.firitin.util.ResizeObserver;

@Route(layout = DefaultLayout.class)
@MenuItem(icon = VaadinIcon.TABLE)
public class ResponsiveGridColumnsView extends VerticalLayout {

    Grid<Person> grid = new VGrid<>(Person.class);

    public ResponsiveGridColumnsView(DataService service) {
        add(new RichText().withMarkDown("""
# Configuring Components based on the device

Some components are just fine both on desktop and mobile, but might need a totally
different kind of configuration, based on the device. A good example is Grid. In many
business apps it is favourable to show a lot of data on large screen devices. But on a
handheld, it seldom makes sense to have more than a couple columns.

The decision in the example is based on grid width. If the width is less
 than 800px, only a column showing combined full name and a column for age is shown, 
 instead of dropping in pretty much all fields of the DTO. In a real life scenario 
 you might want to have several "breakpoints".
                """));

        add(grid);
        grid.setItems(service.getListOfPersons(100));

        ResizeObserver.get().observe(grid, dimensions -> {
            grid.removeAllColumns();
            if(dimensions.width() < 800) {
                grid.addColumn(p -> p.getFirstName() + " " + p.getLastName())
                        .setHeader("Name");
                grid.addColumn("age");
            } else {
                grid.setColumns("id","firstName", "lastName", "age", "joinTime");
            }

        });

        UI.getCurrent().getPage().retrieveExtendedClientDetails(d -> {
            int bodyClientWidth = d.getBodyClientWidth();
            int screenWidth = d.getScreenWidth();
            boolean touchDevice = d.isTouchDevice();
            String timeZoneId = d.getTimeZoneId();
            if(bodyClientWidth < 800) {
                grid.removeAllColumns();
                grid.addColumn(p -> p.getFirstName() + " " + p.getLastName())
                        .setHeader("Name");
            }
        });

        add(grid);
    }
}
