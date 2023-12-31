package app.record.advices;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectAdvices {
    @Before(value = "execution(public * app.record.controllers.RecordController.getRecords(Integer, Integer)) " +
            "&& args(pageNumber, pageSize)", argNames = "pageNumber,pageSize")
    public void beforeGetRecords(Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("pageNumber must be > 0!");
        }

        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be > 0!");
        }
    }
}
