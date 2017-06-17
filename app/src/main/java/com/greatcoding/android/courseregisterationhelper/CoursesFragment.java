package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

import static com.greatcoding.android.courseregisterationhelper.R.id.courseListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoursesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
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
    AlertDialog dialog;
    AlertDialog.Builder builder;
    private String courseSchool = "";
    private String courseSemester = "", courseName= "", courseCampus;
    private ArrayAdapter semAdapter, majAdapter, campusAdapter;
    private Spinner semSpinner, majSpinner, campusSpinner;
    private ListView coursesListView;
    private CoursesListAdapter adapter;
    private List<CoursesMain> coursesList;


    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        campusSpinner = (Spinner) getView().findViewById(R.id.campusSpinner);
        semSpinner = (Spinner) getView().findViewById(R.id.semSpinner);
        majSpinner = (Spinner) getView().findViewById(R.id.majSpinner);
        final RadioGroup courseSchoolGroup = (RadioGroup) getView().findViewById(R.id.courseSchool);
        courseSchoolGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton courseButton = (RadioButton) getView().findViewById(checkedId);

                courseSchool = courseButton.getText().toString();

                    majAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courses, android.R.layout.simple_spinner_dropdown_item);
                    majSpinner.setAdapter(majAdapter);


                    semAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.semester, android.R.layout.simple_spinner_dropdown_item);
                    semSpinner.setAdapter(semAdapter);

                    campusAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.campus, android.R.layout.simple_spinner_dropdown_item);
                    campusSpinner.setAdapter(campusAdapter);


            }

        });

        coursesListView = (ListView) getView().findViewById(R.id.courseListView);
        coursesList = new ArrayList<CoursesMain>();
        adapter = new CoursesListAdapter(getContext().getApplicationContext(), coursesList, this);
        coursesListView.setAdapter(adapter);


        Button searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
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


    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://matched-excuses.000webhostapp.com/ListofCourses1.php?courseSemester=" + URLEncoder.encode(semSpinner.getSelectedItem().toString())
                        + "&courseName=" + URLEncoder.encode(majSpinner.getSelectedItem().toString())
                        + "&courseCampus=" + URLEncoder.encode(campusSpinner.getSelectedItem().toString());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids){

            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch(Exception e){
                e.printStackTrace();

            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }


        @Override
        public void onPostExecute(String result){
            try{

                coursesList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int temp = 0;
                String courseSemester;
                String courseName;
                String courseTitle;
                String courseSeats;
                String courseProf;
                String courseCampus;


                while(temp < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(temp);
                    courseSemester = object.getString("courseSemester");
                    courseName = object.getString("courseName");
                    courseTitle = object.getString("courseTitle");
                    courseSeats = object.getString("courseSeats");
                    courseProf = object.getString("courseProf");
                    courseCampus = object.getString("courseCampus");
                    CoursesMain courses = new CoursesMain(courseSemester, courseName, courseTitle, courseSeats, courseProf, courseCampus);
                    coursesList.add(courses);
                    temp++;
                }

                if(temp == 0){
                    builder = new AlertDialog.Builder(CoursesFragment.this.getActivity());
                    dialog = builder.setMessage("There is no class.").setPositiveButton("Confirm", null).create();
                    dialog.show();
                }

                adapter.notifyDataSetChanged();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}




