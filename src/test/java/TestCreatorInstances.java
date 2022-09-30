import org.junit.jupiter.api.Test;
import ru.axel.creator.instances.CreateInstanceException;
import ru.axel.creator.instances.CreatorInstances;

import java.lang.reflect.InvocationTargetException;

public class TestCreatorInstances {
    @Test
    public void tester() throws InvocationTargetException, InstantiationException, IllegalAccessException, CreateInstanceException {
        CreatorInstances.createInstance(TesterClass.class);
        CreatorInstances.createInstance(TesterClass.class, "text test");
        CreatorInstances.createInstance(TesterClass.class, 1, "number: ");
        CreatorInstances.createInstance(TesterClass.class, 1f);
    }
}

