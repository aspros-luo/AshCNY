package ash.aiqinhaigou.com.ashcny.index.view;

import java.util.List;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;

/**
 * Created by Aspros on 16/5/31.
 */
public interface GoodsView {
    // before load data, show your progress bar
    void showProgress();
    // loading data
    void addData(List<SubjectsBean> goodsBeanList);
    // after load data ,we need to hide progress bar
    void hideProgress();
    // if we make a mistake ,we need to show the msg
    void showFailMsg(String msg,Throwable e);
}
