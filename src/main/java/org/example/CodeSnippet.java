package org.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.dom.Element;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@StyleSheet("https://unpkg.com/@highlightjs/cdn-assets@11.9.0/styles/default.min.css")
@JavaScript("https://unpkg.com/@highlightjs/cdn-assets@11.9.0/highlight.min.js")
@JavaScript("https://unpkg.com/@highlightjs/cdn-assets@11.9.0/languages/java.min.js")
@Tag("pre")
public class CodeSnippet extends Component {

    public CodeSnippet(Class clazz, int startLine, int endLine) {
        try {
            String res = clazz.getName().replaceAll("\\.", "/");
            // This only works if code is exposed to the JAR file...
            InputStream resourceAsStream = getClass().getResourceAsStream("/" + res + ".java");

            List<String> lines = IOUtils.readLines(resourceAsStream).subList(startLine - 1, endLine);
            Pattern p = Pattern.compile("\\S");  // insert your pattern here
            String first = lines.getFirst();
            Matcher m = p.matcher(first);
            if (m.find()) {
                int position = m.start();

                lines =lines.stream().map(s ->
                        s.length() > position ? s.substring(position) : s
                        ).toList();
            }
            Element code = new Element("code");
            code.setText(StringUtils.join(lines, "\n"));
            getElement().appendChild(code);
            getElement().executeJs("hljs.highlightAll();");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
