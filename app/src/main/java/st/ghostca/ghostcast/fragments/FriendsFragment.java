package st.ghostca.ghostcast.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.felipecsl.quickreturn.library.AbsListViewQuickReturnAttacher;
import com.felipecsl.quickreturn.library.QuickReturnAttacher;
import com.felipecsl.quickreturn.library.widget.AbsListViewScrollTarget;
import com.felipecsl.quickreturn.library.widget.QuickReturnAdapter;
import com.felipecsl.quickreturn.library.widget.QuickReturnTargetView;

import st.ghostca.ghostcast.R;

import st.ghostca.ghostcast.fragments.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FriendsFragment extends Fragment implements AbsListView.OnItemClickListener,
        AbsListView.OnScrollListener {

    private int offset;
    private QuickReturnTargetView topTargetView;
    private TextView topTextView;
    private TextView bottomTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FriendsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                //android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
                R.layout.friend_item, R.id.contact_first_name, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        //((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        // IRVIN: 1 for one column in the list view
        ((AdapterView<ListAdapter>) mListView).setAdapter(new QuickReturnAdapter(mAdapter, 1));

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        //////////////////////////////////IRVIN: start
        offset = 0;
        final ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.list);
        bottomTextView = (TextView) view.findViewById(R.id.quickReturnBottomTarget);

/*        if (viewGroup instanceof AbsListView) {
            int numColumns = (viewGroup instanceof GridView) ? 3 : 1;
            final AbsListView absListView = (AbsListView) viewGroup;
            absListView.setAdapter(new QuickReturnAdapter(mAdapter, numColumns));
        }*/

        final QuickReturnAttacher quickReturnAttacher = QuickReturnAttacher.forView(viewGroup);
        quickReturnAttacher.addTargetView(bottomTextView, AbsListViewScrollTarget.POSITION_BOTTOM);
        topTargetView = quickReturnAttacher.addTargetView(topTextView, AbsListViewScrollTarget.POSITION_TOP,
                dpToPx(getActivity(), 50));
        if (quickReturnAttacher instanceof AbsListViewQuickReturnAttacher) {
            // This is the correct way to register an OnScrollListener.
            // You have to add it on the QuickReturnAttacher, instead
            // of on the viewGroup directly.
            final AbsListViewQuickReturnAttacher attacher = (AbsListViewQuickReturnAttacher) quickReturnAttacher;
            attacher.addOnScrollListener(this);
            attacher.setOnItemClickListener(this);
        }
        /////////////// IRVIN: end

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFriendFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFriendFragmentInteraction(String id);
    }

    ///////////
    public static int dpToPx(final Context context, final float dp) {
        // Took from http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {

    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
    }

}
