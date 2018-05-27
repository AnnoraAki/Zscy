package com.example.cynthia.zscy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Remark;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.ViewHolder.AnswerViewHolder;
import com.example.cynthia.zscy.ViewHolder.NoticeViewHolder;
import com.example.cynthia.zscy.ViewHolder.RemarkViewHolder;

import java.util.List;

public class RemarkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NOTICE = 0;
    private static final int TYPE_REMARK = 1;

    private List<Remark> remarks;
    private int aId;
    private String kind;

    public RemarkAdapter(List<Remark> remarks,int aId,String kind){
        this.remarks = remarks;
        this.aId = aId;
        this.kind = kind;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? TYPE_NOTICE : TYPE_REMARK;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_REMARK:
                View view = inflater.inflate(R.layout.recycler_remarks, parent, false);
                return new RemarkViewHolder(view,kind);

            case TYPE_NOTICE:
                View view1 = inflater.inflate(R.layout.recycler_loading, parent, false);
                return new NoticeViewHolder(view1);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RemarkViewHolder) {
            RemarkViewHolder remarkViewHolder = (RemarkViewHolder)holder;
            remarkViewHolder.loadData(remarks.get(position));
        } else {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            String param = "stuNum="+ Application.getAc()+
                    "&idNum="+Application.getPw() +
                    "&page=" + (remarks.size() / 6 + 1 ) + "&size=" + 6 + "&answer_id=" + aId;
            noticeViewHolder.loadData(Config.GET_REMARK_LIST, param, this, NoticeViewHolder.TYPE_REMARK);

        }

    }

    @Override
    public int getItemCount() {
        return remarks.size() + 1;
    }

    public void addRemarks(List<Remark> remarks) {
        this.remarks.addAll(remarks);
        notifyDataSetChanged();
    }
}
