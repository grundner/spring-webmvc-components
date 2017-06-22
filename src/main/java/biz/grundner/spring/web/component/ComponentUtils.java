package biz.grundner.spring.web.component;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyDescriptor;

/**
 * @author Stephan Grundner
 */
public class ComponentUtils {

    public static String getTemplate(Object component) {
        MvcComponent annotation = AnnotationUtils.findAnnotation(
                component.getClass(), MvcComponent.class);
        return annotation.template();
    }

    public static String respond(Object component, Model model) {
        BeanWrapper resultWrapper = new BeanWrapperImpl(component);
        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(component.getClass())) {
            String name = pd.getName();
            Object value = resultWrapper.getPropertyValue(name);
            model.addAttribute(name, value);
        }

        return getTemplate(component);
    }

    public static void respond(Object component, ModelAndView modelAndView) {
        BeanWrapper resultWrapper = new BeanWrapperImpl(component);
        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(component.getClass())) {
            String name = pd.getName();
            Object value = resultWrapper.getPropertyValue(name);
            modelAndView.addObject(name, value);
        }

        String template = getTemplate(component);
        modelAndView.setViewName(template);
    }
}
