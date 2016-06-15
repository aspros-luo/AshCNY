package ash.aiqinhaigou.com.ashcny.shoppingcar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.common.SlideView;
import ash.aiqinhaigou.com.ashcny.model.Utils;

/**
 * Created by Aspros on 16/6/13.
 */
public class ShoppingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SlideView.ISlideViewListener {

    public ShoppingAdapter(Context context) {
        this.mContext = context;
    }

    private Context mContext;

    private List<SubjectsBean> beenData;


    private SlideView mSlideView;

    private OnItemClickListener mOnItemClickListener;

    public void setBeenData(List<SubjectsBean> beenData) {
        this.beenData = beenData;
        notifyDataSetChanged();
    }

    public SubjectsBean getItem(int position) {
        SubjectsBean subjectsBean = beenData.get(position);
        return subjectsBean;
    }

    public void removeBeenData(int position) {
        beenData.remove(position);
        notifyDataSetChanged();
    }

    public void clearBeenData() {
        beenData.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_shopping, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((ItemViewHolder) holder).mLayoutContent.getLayoutParams().width = Utils.getScreenWidth(mContext);
        ((ItemViewHolder) holder).mLayoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();
                } else {
                    int mPosition = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(v, mPosition);
                }
            }
        });

        ((ItemViewHolder) holder).mShoppingDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mPosition = holder.getLayoutPosition();
                mOnItemClickListener.OnItemDelete(v, mPosition);
                closeMenu();
            }
        });
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.)
//                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        SubjectsBean subject = beenData.get(position);
        ImageLoader.getInstance().displayImage(subject.getImages().getMedium(), ((ItemViewHolder) holder).mShoppingImg, options);
        ((ItemViewHolder) holder).mTitle.setText(subject.getTitle());
        ((ItemViewHolder) holder).mSubTitle.setText(subject.getOriginal_title());
    }

    @Override
    public int getItemCount() {
        return beenData.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        mSlideView = (SlideView) view;
    }

    @Override
    public void onDownMove(SlideView slideView) {
        if (menuIsOpen()) {
            if (mSlideView != slideView) {
                closeMenu();
            }
        }
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mShoppingImg;
        TextView mTitle;
        TextView mSubTitle;

        ImageView mShoppingDelete;
        ViewGroup mLayoutContent;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mShoppingImg = (ImageView) itemView.findViewById(R.id.item_shopping_img);
            mTitle = (TextView) itemView.findViewById(R.id.item_shopping_title);
            mSubTitle = (TextView) itemView.findViewById(R.id.item_shopping_subtitle);
            mShoppingDelete = (ImageView) itemView.findViewById(R.id.item_shopping_delete);
            mLayoutContent = (ViewGroup) itemView.findViewById(R.id.item_shopping_content);
            ((SlideView) itemView).setSlideViewListener(ShoppingAdapter.this);
        }

        @Override
        public void onClick(View v) {

            if (mOnItemClickListener != null) {
                mOnItemClickListener.OnItemClick(v, this.getPosition());
            }

        }


    }

    public void closeMenu() {
        mSlideView.closeMenu();
        mSlideView = null;
    }

    public boolean menuIsOpen() {
        if (mSlideView != null) {
            return true;
        }
        return false;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

        void OnItemDelete(View view, int position);
    }
}
