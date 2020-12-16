package Lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClass {
    Constructor<?> constructor;

    public void start(Object object) {
        Class<?> nameClass = object.getClass();
        start(nameClass);
    }

    public void start(Class<?> cl) {
        checkRunTests(cl);
        runBeforeAndAfterSuiteTest(cl, BeforeSuite.class);
        runTests(cl);
        runBeforeAndAfterSuiteTest(cl, AfterSuite.class);
    }

    private void checkRunTests(Class<?> cl) {
        int beforeTest = 0;
        int afterTest = 0;
        Method[] methods = cl.getDeclaredMethods();
        for (Method o : methods) {
            for (Annotation annotation : o.getDeclaredAnnotations()) {
                if (annotation instanceof BeforeSuite) {
                    beforeTest++;
                }
                if (annotation instanceof AfterSuite) {
                    afterTest++;
                }
                if (beforeTest > 1 || afterTest > 1) {
                    throw new RuntimeException("There are more than one instances of 'BeforeTest' or 'AfterTest'!");
                }
            }
        }
    }

    private void runBeforeAndAfterSuiteTest(Class<?> cl, Class<?> annotationClass) {
        try {
            constructor = cl.getConstructor();
            Object object = constructor.newInstance();
            Method[] methods = cl.getDeclaredMethods();
            for (Method o : methods) {
                for (Annotation annotation : o.getDeclaredAnnotations()) {
                    if (annotation.annotationType().isAssignableFrom(annotationClass)) {
                        o.setAccessible(true);
                        o.invoke(object);
                        o.setAccessible(false);
                    }
                }
            }
        } catch (NoSuchMethodException | NullPointerException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    private void runTests(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();
        try {
            constructor = cl.getConstructor();
            Object object = constructor.newInstance();
            for (int i = 1; i <= 10; i++) {
                for (Method o : methods) {
                    for (Annotation annotation : o.getDeclaredAnnotations()) {
                        if (annotation.annotationType().isAssignableFrom(Test.class)) {
                            Test testAnnotation = (Test) annotation;
                            if (testAnnotation.redundant() == i) {
                                o.setAccessible(true);
                                o.invoke(object);
                                o.setAccessible(false);
                            }
                        }
                    }
                }
            }
        } catch (NoSuchMethodException | NullPointerException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
