package exam.defencepreparation.SSB;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by hp on 4/6/2018.
 */

class MyPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mfragments=new ArrayList<Fragment>();
    private String mtittle[] = new String[]{"SSB","OIR","PPDT/TAT", "WAT", "SRT"};



    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void getFragment(Fragment fragment, String tittle)
    {
        mfragments.add(fragment);
        // mtittle.add(tittle);
    }



    @Override
    public Fragment getItem(int position)

    {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }

    @Override

    public CharSequence getPageTitle(int position) {
        return mtittle[position];
    }


}



