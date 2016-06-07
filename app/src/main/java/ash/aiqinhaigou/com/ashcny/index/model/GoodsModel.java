package ash.aiqinhaigou.com.ashcny.index.model;

import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;

/**
 * Created by Aspros on 16/5/31.
 */
public interface GoodsModel {
    //加载列表 方法
    void loadGoods(String url, int type, int pageIndex, GoodsModelImpl.OnLoadGoodsListListener listListener,SubscriberOnNextListener subscriberOnNextListener);

    /*
    方法:加载详细信息
    参数:
    id:商品/电影的id
    listListener:用于监听成功或失败事件
    subscriberOnNextListener:订阅者
     */
    void loadGoodsDetail(String id,GoodsModelImpl.OnLoadGoodsListListener listListener, SubscriberOnNextListener subscriberOnNextListener);
}
