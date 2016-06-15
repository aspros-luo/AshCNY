package ash.aiqinhaigou.com.ashcny.index.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;

/**
 * Created by Aspros on 16/5/31.
 */
public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    private List<SubjectsBean> mData;
    public boolean mShowFooter = true;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    private OnBtnClickListener mOnBtnClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnBtnClickListener(OnBtnClickListener mOnBtnClickListener) {
        this.mOnBtnClickListener = mOnBtnClickListener;
    }

    public SubjectsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public GoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(List<SubjectsBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    public void isShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
//        return TYPE_FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_goods, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder)
        {
            //显示图片的配置
            DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.)
//                .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            SubjectsBean subject = mData.get(position);
            if(subject==null)
            {
                return;
            }
            else
            {

                ImageLoader.getInstance().displayImage(subject.getImages().getLarge(), ((ItemViewHolder) holder).movieImg, options);
                ((ItemViewHolder) holder).movieTitle.setText(subject.getTitle());
                ((ItemViewHolder) holder).movieOriginalTitle.setText(subject.getOriginal_title());
                ((ItemViewHolder) holder).movieYear.setText(subject.getYear());

                String directorsName = "";

                for (SubjectsBean.DirectorsBean directorsBean : subject.getDirectors()) {
                    directorsName = directorsBean.getName();
                }

                ((ItemViewHolder) holder).movieDirector.setText(directorsName);
            }
        }

    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return begin;
        }
        return mData.size() + begin;
//        return mData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnBtnClickListener {
        void onBtnClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movieImg;
        TextView movieTitle;
        TextView movieOriginalTitle;
        TextView movieYear;
        TextView movieDirector;
        Button addShoppingCarBtn;

        public ItemViewHolder(View itemView) {

            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.movie_img);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            movieOriginalTitle = (TextView) itemView.findViewById(R.id.movie_original_title);
            movieDirector = (TextView) itemView.findViewById(R.id.movie_director);
            movieYear = (TextView) itemView.findViewById(R.id.movie_year);
            addShoppingCarBtn = (Button) itemView.findViewById(R.id.addShoppingCar);
            addShoppingCarBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addShoppingCar:
                    if (mOnBtnClickListener != null) {
                        mOnBtnClickListener.onBtnClick(v, this.getPosition());
                    }
                    break;
                case -1:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, this.getPosition());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
