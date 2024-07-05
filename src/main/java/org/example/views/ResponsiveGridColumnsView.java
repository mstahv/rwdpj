package org.example.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.example.CodeSnippet;
import org.example.DefaultLayout;
import org.example.data.DataService;
import org.example.data.domain.Person;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.components.grid.VGrid;
import org.vaadin.firitin.util.ResizeObserver;

@Route(layout = DefaultLayout.class)
@MenuItem(title = "Responsive Grid Cols", icon = VaadinIcon.TABLE)
public class ResponsiveGridColumnsView extends VerticalLayout {

    Grid<Person> grid = new VGrid<>(Person.class);

    public ResponsiveGridColumnsView(DataService service) {
        add(new H1("Configuring Components based on the device"));
        add(new RichText().withMarkDown("""
                Some components are just fine both on desktop and mobile but might need a totally different 
                kind of configuration based on the device. A good example is Grid. In many business apps, 
                it is favorable to show a lot of data on large screen devices. But on a handheld, it seldom 
                makes sense to have more than a couple of columns.

                The decision in the example is based on grid width. If the width is less than 800px, only 
                a column showing the combined full name and a column for age are shown, instead of dropping 
                in pretty much all fields of the DTO. In a real-life scenario, you might want to have several 
                "breakpoints."

                This example uses the [ResizeObserver](https://vaadin.com/directory/component/flow-viritin) 
                helper for simplicity, but similar logic can be implemented using both 
                BrowserWindowResizeListener and "extended client details".
                """));
        add(new CodeSnippet(getClass(), 44, 53));
        add(grid);
        grid.setItems(service.getListOfPersons(100));

        ResizeObserver.get().observe(grid, dimensions -> {
            grid.removeAllColumns();
            if (dimensions.width() < 800) {
                grid.addColumn(p -> p.getFirstName() + " " + p.getLastName())
                        .setHeader("Name");
                grid.addColumn("age");
            } else {
                grid.setColumns("id", "firstName", "lastName", "age", "joinTime");
            }
        });

        add(grid);
    }
}
