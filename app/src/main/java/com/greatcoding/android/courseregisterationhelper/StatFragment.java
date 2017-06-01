package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatFragment newInstance(String param1, String param2) {
        StatFragment fragment = new StatFragment();
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

    private ListView coursesListView;
    private StatisticsListAdapter adapter;
    private List<CoursesMain> coursesList;

    private ArrayAdapter rankingAdapter;
    private Spinner rankingSpinner;

    private ListView rankingListView;
    private RankingListAdapter rankingListAdapter;
    private List<CoursesMain> rankingList;



    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        coursesListView = (ListView) getView().findViewById(R.id.coursesListView);
        coursesList = new ArrayList<CoursesMain>();
        adapter = new StatisticsListAdapter(getContext().getApplicationContext(), coursesList, this);
        coursesListView.setAdapter(adapter);
        new BackgroundTask().execute();
        rankingSpinner = (Spinner) getView().findViewById(R.id.rankingSpinner);
        rankingAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.ranking, R.layout.spinner_items);
        rankingSpinner.setAdapter(rankingAdapter);
        rankingSpinner.setPopupBackgroundResource(R.color.color3);

        rankingListView = (ListView) getView().findViewById(R.id.rankingListView);
        rankingList = new ArrayList<CoursesMain>();
        rankingListAdapter = new RankingListAdapter(getContext().getApplicationContext(), rankingList, this);
        rankingListView.setAdapter(rankingListAdapter);
        new FromAllMajor().execute();
        rankingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rankingSpinner.getSelectedItem().equals("From all major")){

                }else if((rankingSpinner.getSelectedItem().equals("From my major"))){

                }else if((rankingSpinner.getSelectedItem().equals("Men"))){

                }else if((rankingSpinner.getSelectedItem().equals("Women"))){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    class FromAllMajor extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://matched-excuses.000webhostapp.com/FromAllMajor.php";

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
                String courseSemester;
                String courseName;
                String courseTitle;
                String courseProf;
                String courseSeats;
                String courseCampus;

                while (temp < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(temp);
                    courseSemester = object.getString("courseSemester");
                    courseName = object.getString("courseName");
                    courseTitle = object.getString("courseTitle");
                    courseProf = object.getString("courseProf");
                    courseSeats = object.getString("courseSeats");
                    courseCampus = object.getString("courseCampus");
                    rankingList.add(new CoursesMain(courseSemester, courseName, courseTitle, courseProf, courseSeats, courseCampus));
                    temp++;
                }
                rankingListAdapter.notifyDataSetChanged();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://matched-excuses.000webhostapp.com/Statistics.php?userID=" + URLEncoder.encode(MainActivity.userID);

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
                String courseSemester;
                String courseName;
                String courseTitle;
                String courseSeats;
                String courseCampus;

                while (temp < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(temp);
                    courseSemester = object.getString("courseSemester");
                    courseName = object.getString("courseName");
                    courseTitle = object.getString("courseTitle");
                    courseSeats = object.getString("courseSeats");
                    courseCampus = object.getString("courseCampus");
                    coursesList.add(new CoursesMain(courseSemester, courseName, courseTitle, courseSeats, courseCampus));
                    temp++;
                }
                adapter.notifyDataSetChanged();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false);
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
