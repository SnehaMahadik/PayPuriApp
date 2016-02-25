package com.benow.adapters;

public abstract class SelectPreferredBankAdapter<T> {}/*extends RecyclerView.Adapter<SelectPreferredBankAdapter.ViewHolder> {
    public int mSelectedItem = -1;
    public List<T> mItems;
    private Context mContext;

    public SelectPreferredBankAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.rbSelectBank.setChecked(i == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.row_select_preferred_bank, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton rbSelectBank;
        public TextView tvBankName;

        public ViewHolder(final View inflate) {
            super(inflate);
            tvBankName = (TextView) inflate.findViewById(R.id.text);
            rbSelectBank = (RadioButton) inflate.findViewById(R.id.radio);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, mItems.size());
                }
            };
            itemView.setOnClickListener(clickListener);
            rbSelectBank.setOnClickListener(clickListener);
        }
    }

}*/