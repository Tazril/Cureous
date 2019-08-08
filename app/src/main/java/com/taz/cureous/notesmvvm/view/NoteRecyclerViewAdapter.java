package com.taz.cureous.notesmvvm.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.taz.cureous.R;
import com.taz.cureous.notesmvvm.model.Note;

public class NoteRecyclerViewAdapter extends ListAdapter<Note, NoteRecyclerViewAdapter.NoteRecyclerViewHolder> {

    private static DiffUtil.ItemCallback<Note> diffCallback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };
    private OnItemClickListener listener;

    NoteRecyclerViewAdapter() {
        super(diffCallback);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_rv, parent, false);
        return new NoteRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerViewHolder holder, int position) {
        holder.title.setText(getItem(position).getTitle());
        holder.desc.setText(getItem(position).getDescription());
        holder.priority.setText(String.valueOf(getItem(position).getPriority()));

    }

    Note getItemByPosition(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void OnClick(int position);
    }

    class NoteRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, priority;

        NoteRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_note_tv);
            desc = itemView.findViewById(R.id.desc_note_tv);
            priority = itemView.findViewById(R.id.prior_note_tv);
            itemView.setOnClickListener(v -> listener.OnClick(getAdapterPosition()));
        }
    }
}
