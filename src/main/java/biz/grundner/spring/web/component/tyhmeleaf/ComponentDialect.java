package biz.grundner.spring.web.component.tyhmeleaf;

import biz.grundner.spring.web.component.tyhmeleaf.processor.ComponentIncludeProcessor;
import biz.grundner.spring.web.component.tyhmeleaf.processor.ComponentReplaceProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Stephan Grundner
 */
public class ComponentDialect extends AbstractProcessorDialect {

    private static final String PREFIX = "component";

    protected ComponentReplaceProcessor createComponentReplaceProcessor() {
        return new ComponentReplaceProcessor(PREFIX, getDialectProcessorPrecedence() * 10);
    }

    protected ComponentIncludeProcessor createComponentIncludeProcessor() {
        return new ComponentIncludeProcessor(PREFIX, getDialectProcessorPrecedence() * 10 + 1);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        HashSet<IProcessor> processors = new HashSet<>();
        processors.add(createComponentReplaceProcessor());
        processors.add(createComponentIncludeProcessor());

        return processors;
    }

    public ComponentDialect() {
        super("Component Dialect", PREFIX, 1000);
    }
}
