import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheableInvocationHandler implements InvocationHandler {
    private Object realObj;
    private Map<String, Object> cachedValues = new HashMap<>();


    public CacheableInvocationHandler(Object realObj ){
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cacheable cacheableAnnotation = realObj.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cacheable.class);
        if (cacheableAnnotation != null){
            String cacheId= cacheableAnnotation.value();
            if (cachedValues.containsKey(cacheId)){
                return cachedValues.get(cacheId);
            }else {
                var result =  method.invoke(realObj,args);
                cachedValues.put(cacheId, result);
                return result;
            }
        }else {
            return method.invoke(realObj,args);
        }
    }
}
