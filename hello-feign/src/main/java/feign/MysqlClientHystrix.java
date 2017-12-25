package feign;

import org.springframework.stereotype.Component;

@Component
public class MysqlClientHystrix implements MysqlFeignClient {
    @Override
    public Object add(Object user) {
        return "ADD,SERVICE FAILED";
    }

    @Override
    public Object delete(String userId, String userCode) {
        return "DELETE,SERVICE FAILED";
    }

    @Override
    public Object update(Object user) {
        return "UPADTE,SERVICE FAILED";
    }

    @Override
    public Object detail(String userId, String userCode) {
        return "DETAIL,SERVICE FAILED";
    }

    @Override
    public Object list() {
        return "LIST,SERVICE FAILED";
    }

    @Override
    public Object getInfos(String select, String parameter, int pageNum, int pageSize) {
        return "GETINFOS,SERVICE FAILED";
    }
}
