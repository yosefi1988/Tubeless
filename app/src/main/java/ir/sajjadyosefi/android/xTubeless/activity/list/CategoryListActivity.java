package ir.sajjadyosefi.android.xTubeless.activity.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.Category;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory.ListItemType.TYPE_EMPTY_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory.ListItemType.TYPE_LAST_ITEM;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static org.litepal.LitePalApplication.getContext;


public class CategoryListActivity extends Activity {

    private static final String TAG = "sssssssssssssss";
    private int RC_SIGN_IN = 1000;
    private List<Category> categoryList;
    public int categoryCount = 1;
    Context context;


    RecyclerView mRecyclerViewTimeline;
    EndlessList_AdapterCategory adapter;
    LinearLayoutManager mLayoutManager;


    Button buttonBack,buttonClear , buttonAddCategory;
    TextView textViewNameFamily1,TextViewSerial,textViewDate,textViewNameFamily2,textViewMobile,TextViewCodePosti,TextViewAddress;

    public static Activity activity ;
    private View rootView = null;

    public static ImageView imageView ;

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context, CategoryListActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            int payType = data.getIntExtra("CatID" , 0);
            Bundle dd = data.getExtras();
            dd.getString("CatIDX");
            String selectedItemString = dd.getString("SelectedObject");
//            setResult(Activity.RESULT_OK, data);
//            finish();


            Gson gson = new Gson();
            CategoryItem selectedObject = gson.fromJson(selectedItemString, CategoryItem.class);

            if (checkExist(selectedObject)){
                Toast.makeText(getContext(),"آیتم تکراری انتخاب کرده اید" , Toast.LENGTH_SHORT).show();
            }else {
                if (payType != 0) {
                    if (categoryList.size() == 2 && categoryList.get(1).getListItemType() == TYPE_EMPTY_LIST) {
                        categoryList.remove(1);
                    }


                    Category category = new Category();
                    category.setListItemType(EndlessList_AdapterCategory.ListItemType.TYPE_ITEM);

                    category.setID(selectedObject.getId());
                    category.setName(selectedObject.getCatName());
                    category.setImage(selectedObject.getImage());
                    category.setStatement(selectedObject.getStatment());

                    categoryList.add(1,category);
                    adapter.notifyDataSetChanged();


                    if (categoryList.size() - 1 == categoryCount) {
                        Category Emptylist = new Category();
                        Emptylist.setListItemType(TYPE_LAST_ITEM);
                        categoryList.add(Emptylist);
                        adapter.notifyDataSetChanged();
                    }

                }


            }
        }else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }

    private boolean checkExist(CategoryItem selectedObject) {
        boolean retVal = false;
        for (Category item: categoryList) {
            if (item.getID() == selectedObject.getId())
                retVal = true;
        }
        return retVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        context = this;
        categoryCount = getIntent().getIntExtra("CAT_COUNT",1);
        categoryList = (List<Category>) getIntent().getSerializableExtra("LIST");

        setContentView(R.layout.activity_categories_list);
        rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        buttonClear = findViewById(R.id.buttonClear);
        buttonAddCategory = findViewById(R.id.buttonAddCategory);
        buttonBack = findViewById(R.id.buttonBack);
        textViewDate = findViewById(R.id.textViewDate);


        mRecyclerViewTimeline = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.imageView);

        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type" , TYPE_LIST);
                bundle.putInt("CAT_COUNT", categoryCount);
                ((Activity)context).startActivityForResult(ContainerActivity.getIntent(context,bundle),3333);
            }
        });

        ((Button)(findViewById(R.id.buttonClear))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryList = new ArrayList<>();

                Category headerInList = new Category();
                headerInList.setListItemType(EndlessList_AdapterCategory.ListItemType.TYPE_HEADER);
                categoryList.add(headerInList);

                Category Emptylist = new Category();
                Emptylist.setListItemType(TYPE_EMPTY_LIST);
                categoryList.add(Emptylist);

                fillWigets(rootView , activity);
                adapter.notifyDataSetChanged();

                buttonAddCategory.setEnabled(true);



//                //rxjava rxandroid sample 1
//                Observable<String> animalsObservable = Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
//                Observer<String> animalsObserver = getAnimalsObserver();
//
//                animalsObservable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(animalsObserver);
//
//
//
//                //rxjava rxandroid sample 2
//                Flowable.just("Hello world").subscribe(System.out::println);
//
//
//                //Observable و Observer
//                //rxjava rxandroid sample 3
//                Observable<Note> notes = getObservableNote();
//                Observer<Note> notesObserver = getNotesObserver();
//
//                notes
//                        .observeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(notesObserver);
//
//
//                //Single و SingleObserver
//                //rxjava rxandroid sample 4
//                Single<Note> noteObservable = getNoteObservable();
//                SingleObserver<Note> singleObserver = getSingleObserver();
//
//                noteObservable
//                        .observeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe(singleObserver);
//
//
//                //Maybe و MaybeObserver
//                //rxjava rxandroid sample 5
//                Maybe<Note> noteObservableX = getNoteObservablex();
//                MaybeObserver<Note> noteObserver = getNoteObserver();
//
//                noteObservableX.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(noteObserver);
//
//
//
//                //Completable و CompletableObserver
//                //rxjava rxandroid sample 5
//                Note note = new Note(1, "Home work!");
//                Completable completableObservable = updateNote(note);
//                CompletableObserver completableObserver = completableObserverC();
//                completableObservable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(completableObserver);
//
//
//                //Flowable و Observer
//                //rxjava rxandroid sample 6
//                Flowable<Integer> flowableObservable = getFlowableObservable();
//                SingleObserver<Integer> observer = getFlowableObserver();
//                flowableObservable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .reduce(0, new BiFunction<Integer, Integer, Integer>() {
//                            @Override
//                            public Integer apply(Integer result, Integer number) {
//                                //Log.e(TAG, "Result: " + result + ", new number: " + number);
//                                return result + number;
//                            }
//                        })
//                        .subscribe(observer);
//

            }
        });
        findViewById(R.id.buttonCancel).setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        });

        findViewById(R.id.buttonOK).setOnClickListener(view -> {
            List<Category> returnedcategoryList = new ArrayList<>();
            for (Category category:categoryList) {
                if (category.getListItemType() == EndlessList_AdapterCategory.ListItemType.TYPE_ITEM){
                    returnedcategoryList.add(category);
                }
            }

            Intent returnIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("LIST", (Serializable) returnedcategoryList);
            returnIntent.putExtras(bundle);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

//                if (lastCheckedPosition != -1) {
//                    for (Category item : categoryList) {
//                        item.setContentPic(false);
//                    }
//                    categoryList.get(lastCheckedPosition).setContentPic(true);
//                }
//                if (lastCheckedPosition2 != -1) {
//                    for (Category item : categoryList) {
//                        item.setHeaderPic(false);
//                    }
//                    categoryList.get(lastCheckedPosition2).setHeaderPic(true);
//                }
//
//                Intent returnIntent = getIntent();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("LIST1", (Serializable) categoryList);
////                returnIntent.putExtra("LIST2", (Serializable) fileList);
//                returnIntent.putExtras(bundle);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
        });

        fillWigets(rootView , activity);



//        try {
//            openFile(DetailsActivity.activity,path+"/arzyabi8331.jpg",false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        refreshButtons();
    }

    public void deleteDone() {
        if (categoryList.size() >= categoryCount + 1) {
            if (categoryList.get(categoryList.size() - 1).getListItemType() == TYPE_LAST_ITEM) {
                categoryList.remove(categoryList.size() - 1);
                adapter.notifyDataSetChanged();
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    public void refreshButtons() {
        if (categoryCount + 2 == categoryList.size()) {
            buttonAddCategory.setEnabled(false);
        }else {
            buttonAddCategory.setEnabled(true);
        }




        if (categoryList.size() == 1) {
            Category Emptylist = new Category();
            Emptylist.setListItemType(TYPE_EMPTY_LIST);
            categoryList.add(Emptylist);
            adapter.notifyDataSetChanged();
        }
    }

    private Observer<Note> getNotesObserver() {
        return new Observer<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Note note) {
                Log.d(TAG, "onNext: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }

    private Observable<Note> getObservableNote() {
        final List<Note> notes = prepareNotes();

        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) throws Exception {
                for (Note note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }

                // all notes are emitted
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }

            }
        });
    }

    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Buy tooth paste!"));
        notes.add(new Note(2, "Call brother!"));
        notes.add(new Note(3, "Watch Narcos tonight!"));
        notes.add(new Note(4, "Pay power bill!"));
        return notes;
    }

    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }


    private void fillWigets(View rootView , Activity activity) {
        mRecyclerViewTimeline.setHasFixedSize(true);
        mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new EndlessList_AdapterCategory(
                context,
                mLayoutManager,
                rootView,
                categoryList,
                EndlessList_AdapterCategory.ListType.LIST_OF_PICTURES,
                true);
        mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
        mRecyclerViewTimeline.setAdapter(adapter);
    }

    private SingleObserver<Note> getSingleObserver() {
        return new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onSuccess(Note note) {
                Log.e(TAG, "onSuccess: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
    }

    private Single<Note> getNoteObservable() {
        return Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                Note note = new Note(1, "Buy milk!");
                emitter.onSuccess(note);
            }
        });
    }

    private MaybeObserver<Note> getNoteObserver() {
        return new MaybeObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Note note) {
                Log.d(TAG, "onSuccess: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
    }

    /**
     * Emits optional data (0 or 1 emission)
     * But for now it emits 1 Note always
     */
    private Maybe<Note> getNoteObservablex() {
        return Maybe.create(new MaybeOnSubscribe<Note>() {
            @Override
            public void subscribe(MaybeEmitter<Note> emitter) throws Exception {
                Note note = new Note(1, "Call brother!");
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(note);
                }
            }
        });
    }

    private Completable updateNote(Note note) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Thread.sleep(1000);
                    emitter.onComplete();
                }
            }
        });
    }

    private CompletableObserver completableObserverC() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Note updated successfully!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        };
    }

    private SingleObserver<Integer> getFlowableObserver() {
        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d(TAG, "onSuccess: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        };
    }

    private Flowable<Integer> getFlowableObservable() {
        return Flowable.range(1, 100);
    }




    class Note {
        public int id;
        public String text;

        public Note(int i, String s) {
            this.id = i;
            this.text = s;
        }

        public String getNote() {
            return id + text;
        }
    }
}
