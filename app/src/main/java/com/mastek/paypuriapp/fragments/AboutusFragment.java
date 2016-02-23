package com.mastek.paypuriapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastek.paypuriapp.MainActivity;
import com.mastek.paypuriapp.R;
import com.mastek.paypuriapp.activity.TermsnConditionsActivity;


public class AboutusFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private TextView textViewTermsnConditions,textViewPrivacyPolicy;
    private FragmentTransaction fragmentTransaction;

    public AboutusFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("About Paypuri");
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        // Inflate the layout for this fragment
        textViewPrivacyPolicy=(TextView)view.findViewById(R.id.textViewPrivacyPolicy);
        textViewTermsnConditions=(TextView)view.findViewById(R.id.textViewTermsnConditions);

        textViewPrivacyPolicy.setOnClickListener(this);
        textViewTermsnConditions.setOnClickListener(this);
        return view;


    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new DashboardFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.textViewTermsnConditions:

                Intent intent = new Intent(getActivity(), TermsnConditionsActivity.class);
                Bundle b = new Bundle();
                b.putString("DATA",getActivity().getString(R.string.privacy_policy_text));
                b.putString("TITLE", "Terms and Conditions");
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.textViewPrivacyPolicy:
                Intent intent1 = new Intent(getActivity(), TermsnConditionsActivity.class);
                Bundle b1 = new Bundle();
                b1.putString("DATA",getActivity().getString(R.string.privacy_policy_text));
                b1.putString("TITLE", "Privacy Policy");
                intent1.putExtras(b1);
                startActivity(intent1);
                default:
                    break;
        }

    }

    public void changeFragment(Fragment fragment, String tag){
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
}
