package com.example.sharedpreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<UserInfo> userInfos;

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userInfos = userInfos;
    }

    @Override
    public int getCount() {
        //有多少条数据
        return userInfos.size();
    }

    @Override
    public Object getItem(int i) {
        //要找到哪一条数据记录，每个数据记录对象内又有很多个对应的数据
        return userInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        //每条数据记录的ID
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 每一行数据显示在界面上，用户能够看到时
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {//返回数据的一个视图，该视图在item_phone_book_friend中给出
        //将控件构成一个类！
        ViewHolder viewHolder;
        //获得Layout，将item_phone_book_friend的layout加载到view中
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_phone_book_friend, null);
            viewHolder = new ViewHolder();
            //找到该视图上的内容，填充每个数据
            viewHolder.nameTextView     = view.findViewById(R.id.name_text_view);
            viewHolder.ageTextView      = view.findViewById(R.id.age_text_view);
            viewHolder.avatarImageView  = view.findViewById(R.id.avatar_image_view);
            view.setTag(viewHolder);    //打标签，保存起来！
        }
        else{
            //不是第一次，则直接从标签中获得！
            viewHolder = (ViewHolder) view.getTag();
        }

        //和数据之间进行绑定
        viewHolder.nameTextView.setText(userInfos.get(i).getmUserName());
        viewHolder.ageTextView.setText(String.valueOf(userInfos.get(i).getmAge()));
        viewHolder.avatarImageView.setImageResource(R.drawable.ic_launcher_background);

        return view;
    }

    class ViewHolder{
        TextView nameTextView;
        TextView ageTextView;
        ImageView avatarImageView;
    }

    /**
     * 替换老的数据
     * 刷新ListView，让他更新自己的视图
     * @param userInfos
     */
    public void refreshData(List<UserInfo> userInfos){
        //使用新的数据来更新
        this.userInfos = userInfos;
        notifyDataSetChanged();     //提示data改变了
    }
}