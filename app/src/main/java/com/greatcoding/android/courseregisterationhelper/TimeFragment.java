package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeFragment newInstance(String param1, String param2) {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView firstYr[] = new TextView[6];
    private TextView secondYr[] = new TextView[6];
    private TextView thirdYr[] = new TextView[6];
    private TextView fourthYr[] = new TextView[6];
    private Schedule schedule = new Schedule();

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        firstYr[0] = (TextView) getView().findViewById(R.id.firstYr0);
        firstYr[1] = (TextView) getView().findViewById(R.id.firstYr1);
        firstYr[2] = (TextView) getView().findViewById(R.id.firstYr2);
        firstYr[3] = (TextView) getView().findViewById(R.id.firstYr3);
        firstYr[4] = (TextView) getView().findViewById(R.id.firstYr4);
        firstYr[5] = (TextView) getView().findViewById(R.id.firstYr5);

        secondYr[0] = (TextView) getView().findViewById(R.id.secondYr0);
        secondYr[1] = (TextView) getView().findViewById(R.id.secondYr1);
        secondYr[2] = (TextView) getView().findViewById(R.id.secondYr2);
        secondYr[3] = (TextView) getView().findViewById(R.id.secondYr3);
        secondYr[4] = (TextView) getView().findViewById(R.id.secondYr4);
        secondYr[5] = (TextView) getView().findViewById(R.id.secondYr5);

        thirdYr[0] = (TextView) getView().findViewById(R.id.thirdYr0);
        thirdYr[1] = (TextView) getView().findViewById(R.id.thirdYr1);
        thirdYr[2] = (TextView) getView().findViewById(R.id.thirdYr2);
        thirdYr[3] = (TextView) getView().findViewById(R.id.thirdYr3);
        thirdYr[4] = (TextView) getView().findViewById(R.id.thirdYr4);
        thirdYr[5] = (TextView) getView().findViewById(R.id.thirdYr5);

        fourthYr[0] = (TextView) getView().findViewById(R.id.fourthYr0);
        fourthYr[1] = (TextView) getView().findViewById(R.id.fourthYr1);
        fourthYr[2] = (TextView) getView().findViewById(R.id.fourthYr2);
        fourthYr[3] = (TextView) getView().findViewById(R.id.fourthYr3);
        fourthYr[4] = (TextView) getView().findViewById(R.id.fourthYr4);
        fourthYr[5] = (TextView) getView().findViewById(R.id.fourthYr5);

        new BackgroundTask().execute();




    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://matched-excuses.000webhostapp.com/ListofSchedule.php?userID=" + URLEncoder.encode(MainActivity.userID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }


        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int temp = 0;
                String courseName;
                String courseTitle;
                String courseCampus;

                while (temp < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(temp);
                    courseName = object.getString("courseName");
                    courseTitle = object.getString("courseTitle");
                    courseCampus = object.getString("courseCampus");
                    schedule.addSchedule(courseName, courseTitle, courseCampus);
                    temp++;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            schedule.showSetting(firstYr, secondYr, thirdYr, fourthYr, getContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
