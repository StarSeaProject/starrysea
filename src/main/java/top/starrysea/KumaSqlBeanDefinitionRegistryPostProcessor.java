package top.starrysea;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

import top.starrysea.kql.facede.KumaSqlDaoImpl;

@Configuration
public class KumaSqlBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 什么都不做
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registerBean(registry, "kumaSqlDao", KumaSqlDaoImpl.class);
	}

	private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
		AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);

		ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
		abd.setScope(scopeMetadata.getScopeName());
		// 可以自动生成name
		String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));

		AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
		BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
	}

}
