package hudson.plugins.view.dashboard.core;

import hudson.Extension;
import hudson.MarkupText;
import hudson.model.Descriptor;
import hudson.plugins.view.dashboard.DashboardPortlet;
import hudson.plugins.view.dashboard.Messages;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import hudson.markup.MarkupFormatter;
import hudson.markup.MarkupFormatterDescriptor;

public class MarkupFormatterPortlet extends DashboardPortlet {

    private String text;
    private final String divStyle;

    @DataBoundConstructor
    public MarkupFormatterPortlet(String name, String divStyle) {
        super(name);
        this.divStyle = divStyle;
    }


    public String getText() {
        return this.text;
    }

    public String getDivStyle() {
        return divStyle;
    }

    public MarkupFormatter getUserSelectedFormatter() {
        // Find the descriptor that matches the user selection
        for (MarkupFormatterDescriptor descriptor : MarkupFormatter.all()) {
            if (descriptor.getId().equals(this.markupFormatterId)) {
                return descriptor.newInstance(null, null); // instantiate the formatter
            }
        }
        // fallback to default
        return hudson.MarkupFormatter.descriptor().newInstance(null, null);
    }

    @DataBoundSetter
    public void setText(String text) {
        this.text = text;


        // MarkupText markup = new MarkupText(text);
        // var formatter = getUserSelectedFormatter();
        // formatter.decorate(markup);
        // this.text = markup.toString(true); // safe HTML ready for Jelly

    }

    @Extension
    public static class DescriptorImpl extends Descriptor<DashboardPortlet> {

        @Override
        public String getDisplayName() {
            return Messages.Dashboard_MarkupFormatterPortlet();
        }
    }
}
