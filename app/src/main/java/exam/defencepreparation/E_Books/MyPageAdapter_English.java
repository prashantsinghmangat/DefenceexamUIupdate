package exam.defencepreparation.E_Books;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by hp on 5/28/2018.
 */

public class MyPageAdapter_English extends FragmentPagerAdapter {

    ArrayList<Fragment> mfragments=new ArrayList<Fragment>();
    private String mtittle[] = new String[]{"Magazine", "Notes", "E-Books"};

    public MyPageAdapter_English(FragmentManager fm) {
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






