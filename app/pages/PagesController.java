package pages;

import com.commercetools.sunrise.framework.components.controllers.PageHeaderControllerComponentSupplier;
import com.commercetools.sunrise.framework.components.controllers.RegisteredComponents;
import com.commercetools.sunrise.framework.controllers.SunriseContentController;
import com.commercetools.sunrise.framework.controllers.cache.NoCache;
import com.commercetools.sunrise.framework.hooks.EnableHooks;
import com.commercetools.sunrise.framework.template.TemplateControllerComponentsSupplier;
import com.commercetools.sunrise.framework.template.engine.ContentRenderer;
import com.commercetools.sunrise.framework.viewmodels.content.PageContent;
import play.libs.concurrent.HttpExecution;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@NoCache
@RegisteredComponents({
        TemplateControllerComponentsSupplier.class,
        PageHeaderControllerComponentSupplier.class
})
public final class PagesController extends SunriseContentController {

    @Inject
    public PagesController(final ContentRenderer contentRenderer) {
        super(contentRenderer);
    }

    @EnableHooks
    public CompletionStage<Result> show(final String languageTag, final String cmsKey) {
        return getContentRenderer().render(new PageContent() {}, "pages", cmsKey)
                .thenApplyAsync(Results::ok, HttpExecution.defaultContext());
    }
}
