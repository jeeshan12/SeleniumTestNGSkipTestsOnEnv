import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;
import java.util.function.Predicate;

public class MethodListener implements IMethodInterceptor {


    private Predicate<IMethodInstance> methodInstancePredicate = (methodInstance) -> methodInstance.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(NotProduction.class);


    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        String env =  context.getCurrentXmlTest().getParameter("env");
        Predicate<IMethodInstance> envPredicate = (methodInstance) -> env.equalsIgnoreCase("prod");
        boolean isTestRemoved = methods.removeIf(envPredicate.and(methodInstancePredicate));

        methods.stream().
                map(IMethodInstance::getMethod)
                .forEach(m -> m.setIgnoreMissingDependencies(isTestRemoved));
        return methods;
    }
}
