package nl.wjglerum;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/metrics")
public class MetricResource {

    @Inject
    Template page;

    @CheckedTemplate
    private static class Templates {
        public static native TemplateInstance strict(Metrics metrics);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        Metrics metrics = ImmutableMetrics.builder()
                .requests(ImmutableCount.of(1))
                .responses(ImmutableCount.of(2))
                .build();
        return page.data("metrics", metrics);
    }

    @GET
    @Path("/strict")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance strict() {
        Metrics metrics = ImmutableMetrics.builder()
                .requests(ImmutableCount.of(1))
                .responses(ImmutableCount.of(2))
                .build();
        return Templates.strict(metrics);
    }
}
