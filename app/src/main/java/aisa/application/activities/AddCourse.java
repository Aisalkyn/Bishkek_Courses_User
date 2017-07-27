package aisa.application.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import aisa.application.R;
import aisa.application.adapter.AdapterBranches;
import aisa.application.adapter.AdapterCategories;
import aisa.application.adapter.AdapterContactTypes;
import aisa.application.adapter.AdapterContacts;
import aisa.application.adapter.AdapterImages;
import aisa.application.adapter.AdapterServices;
import aisa.application.adapter.AdapterSubCategory;
import aisa.application.model.Branches;
import aisa.application.model.Categories;
import aisa.application.model.ContactType;
import aisa.application.model.Contacts;
import aisa.application.model.Courses;
import aisa.application.items.ForumService;
import aisa.application.model.Images;
import aisa.application.model.Services;
import aisa.application.model.SubCategories;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.items.ForumService.builder;

public class AddCourse extends AppCompatActivity {

    private static final int LOCATION_PERMISSION = 100;
    @BindView(R.id.name)
    TextView nameView;
    @BindView(R.id.description)
    TextView descriptionView;
    @BindView(R.id.listServices)
    ListView listViewServices;
    @BindView(R.id.listBranches)
    ListView listViewBranches;
    @BindView(R.id.listContacts)
    ListView listViewContacts;
    @BindView(R.id.listCategories)
    ListView listViewCategories;
    @BindView(R.id.listImages)
    ListView listViewImages;

    AdapterServices adapterService;
    AdapterBranches adapterBranch;
    AdapterContacts adapterContact;
    AdapterCategories adapterCategory;
    AdapterImages adapterImage;

    List<Services> listService = new ArrayList<>();
    List<Branches> listBranch = new ArrayList<>();
    List<Contacts> listContact = new ArrayList<>();
    List<Categories> listCategory = new ArrayList<>();
    List<Images> listImage = new ArrayList<>();

    List<Categories> listCategories = new ArrayList<>();

    GoogleMap mMap = null;
    LatLng latLng = null;
    ForumService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        service = builder.create(ForumService.class);

        listViewServices.setAdapter(adapterService = new AdapterServices(getApplicationContext(), listService));
        listViewBranches.setAdapter(adapterBranch = new AdapterBranches(getApplicationContext(), listBranch));
        listViewContacts.setAdapter(adapterContact = new AdapterContacts(getApplicationContext(), listContact));
        listViewCategories.setAdapter(adapterCategory = new AdapterCategories(getApplicationContext(), listCategory));
        listViewImages.setAdapter(adapterImage = new AdapterImages(getApplicationContext(), listImage));
    }

    @OnItemClick(R.id.listServices)
    public void onServiceItemClick(AdapterView<?> parent, View view, int position, long id) {
        openService(position);
    }

    @OnItemClick(R.id.listBranches)
    public void onBranchItemClick(AdapterView<?> parent, View view, int position, long id) {
        openBranches(position);
    }

    @OnItemClick(R.id.listContacts)
    public void onContactItemClick(AdapterView<?> parent, View view, int position, long id) {
        openContacts(position);
    }

    @OnItemClick(R.id.listCategories)
    public void onCategoryItemClick(AdapterView<?> parent, View view, int position, long id) {
        openCategories(position);
    }

    @OnItemClick(R.id.listImages)
    public void onImageItemClick(AdapterView<?> parent, View view, int position, long id) {
        openImages(position);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void sendCreateCourseRequest(Courses courses) {
        builder.create(ForumService.class).createCourse(courses).enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                showMessage(response.message());
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    @OnClick(R.id.services)
    public void clickService() {
        openService(-1);
    }

    @OnClick(R.id.branches)
    public void clickBranch() {
        openBranches(-1);
    }

    @OnClick(R.id.contacts)
    public void clickContact() {
        openContacts(-1);
    }

    @OnClick(R.id.categories)
    public void clickCategory() {
        openCategories(-1);
    }

    @OnClick(R.id.images)
    public void clickImage() {
        openImages(-1);
    }

    @OnClick(R.id.save)
    public void clickSave() {
        String name = nameView.getText().toString();
        String descr = descriptionView.getText().toString();
        if (name.isEmpty() || descr.isEmpty()) {
            showMessage("Name and description required");
        } else {
            Courses courses = new Courses(name,
                    descr,
                    listBranch,
                    listContact,
                    listService,
                    listCategory,
                    listImage);
            sendCreateCourseRequest(courses);
        }
    }

    @OnClick(R.id.cancel)
    public void clickCancel(View v) {
        finish();
    }

    private void openService(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить услугу");
        builder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_service_dialog, null);
        builder.setView(dialogView);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Save", null);

        final EditText nameView = (EditText) dialogView.findViewById(R.id.name);
        final EditText descriptionView = (EditText) dialogView.findViewById(R.id.description);
        final EditText priceView = (EditText) dialogView.findViewById(R.id.price);

        if (position != -1) {
            Services service = listService.get(position);
            nameView.setText(service.getName());
            descriptionView.setText(service.getDescription());
            priceView.setText(String.valueOf(service.getPrice()));
        }

        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                positive.setOnClickListener(branchDialogClick);
                negative.setOnClickListener(branchDialogClick);
            }

            private View.OnClickListener branchDialogClick = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (view == alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)) {
                        alertDialog.dismiss();
                    } else {
                        String name = nameView.getText().toString();
                        String price = priceView.getText().toString();
                        String desc = descriptionView.getText().toString();
                        if (name.isEmpty() || price.isEmpty() || desc.isEmpty()) {
                            showMessage("No empty field is allowed");
                        } else {
                            try {
                                int priceNum = Integer.parseInt(price);
                                if (position != -1) {
                                    listService.set(position, new Services(name, priceNum, desc));
                                    alertDialog.dismiss();
                                } else {
                                    listService.add(new Services(name, priceNum, desc));
                                    alertDialog.dismiss();
                                }
                                expandeView(listViewServices, adapterService);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                showMessage("Incorrect Number");
                            }
                        }
                    }
                }
            };
        });
        alertDialog.show();
    }

    private void openBranches(final int position) {
        FragmentManager fm = getSupportFragmentManager();
        latLng = null;
        Fragment xmlFragment = fm.findFragmentById(R.id.map);
        if (xmlFragment != null) {
            fm.beginTransaction().remove(xmlFragment).commit();
            fm.executePendingTransactions();
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_branch_dialog, null);
        builder.setView(dialogView);

        final EditText addressView = (EditText) dialogView.findViewById(R.id.address);
        final EditText phoneView = (EditText) dialogView.findViewById(R.id.phone);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.getUiSettings().setZoomControlsEnabled(true);
                latLng = (latLng == null) ? new LatLng(42.882004f, 74.582748f) : latLng;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                        .position(latLng).title("Bishkek"));
                requestLocationPermission();
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng point) {
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().icon(
                                BitmapDescriptorFactory
                                        .fromResource(R.drawable.location))
                                .anchor(0.0f, 1.0f)
                                .position(latLng = point));
                    }
                });
            }
        });

        if (position != -1) {
            Branches branch = listBranch.get(position);
            addressView.setText(branch.getAddress());
            phoneView.setText(branch.getPhone());
            latLng = new LatLng(Double.parseDouble(branch.getLatitude()), Double.parseDouble(branch.getLongitude()));
        }

        builder.setCancelable(false);
        builder.setPositiveButton("Save", null);
        builder.setNegativeButton("Cancel", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                positive.setOnClickListener(branchDialogClick);
                negative.setOnClickListener(branchDialogClick);
            }

            private View.OnClickListener branchDialogClick = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (view == alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)) {
                        alertDialog.dismiss();
                    } else {
                        String address = addressView.getText().toString();
                        String phone = phoneView.getText().toString();
                        if (address.isEmpty() || phone.isEmpty()) {
                            showMessage("No empty field is allowed");
                        } else {
                            Branches branches = new Branches(
                                    address, phone,
                                    String.valueOf(latLng.latitude),
                                    String.valueOf(latLng.longitude));
                            if (position != -1) {
                                listBranch.set(position, branches);
                                alertDialog.dismiss();
                            } else {
                                listBranch.add(branches);
                                alertDialog.dismiss();
                            }
                            expandeView(listViewBranches, adapterBranch);
                        }
                    }
                }
            };
        });
        alertDialog.show();
    }

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddCourse.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
        } else {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION) {
            requestLocationPermission();
        }
    }

    private void openContacts(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить контакт");
        builder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_contact_dialog, null);
        builder.setView(dialogView);

        final Spinner spinnerView = (Spinner) dialogView.findViewById(R.id.spinner);
        final EditText contactView = (EditText) dialogView.findViewById(R.id.yourInput);
        getContactTypesRequest(spinnerView, contactView, position);
        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ContactType type = (ContactType) parent.getItemAtPosition(pos);
                if (type.getName().equals("Телефон") || type.getName().equals("WhatsApp"))
                    contactView.setInputType(InputType.TYPE_CLASS_PHONE);
                else if (type.getName().equals("Почта"))
                    contactView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                else
                    contactView.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
                contactView.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = contactView.getText().toString();

                if (input.isEmpty()) {
                    showMessage("No empty field is allowed");
                } else {
                    Contacts contact = new Contacts(((ContactType) spinnerView.getSelectedItem()).getType(), input);
                    if (position != -1) {
                        listContact.set(position, contact);
                        dialog.dismiss();
                    } else {
                        listContact.add(contact);
                        dialog.dismiss();
                    }
                    expandeView(listViewContacts, adapterContact);
                }
            }
        }).show();

    }

    public void getContactTypesRequest(final Spinner spinner, final EditText contactView, final int position) {
        service.getALLContactTypes().enqueue(new Callback<List<ContactType>>() {
            @Override
            public void onResponse(Call<List<ContactType>> call, Response<List<ContactType>> response) {
                spinner.setAdapter(new AdapterContactTypes(getApplicationContext(), response.body()));
                if (position != -1) {
                    Contacts contact = listContact.get(position);
                    spinner.setSelection(((AdapterContactTypes) spinner.getAdapter()).getPos(contact.getContactType()));
                    contactView.setText(contact.getData());
                }
            }

            @Override
            public void onFailure(Call<List<ContactType>> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    private void openCategories(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить категорию");
        builder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_category_dialog, null);
        builder.setView(dialogView);

        final Spinner spinnerCategory = (Spinner) dialogView.findViewById(R.id.spinnerCat);
        final Spinner spinnerSubCat = (Spinner) dialogView.findViewById(R.id.spinnerSubCat);
        getCategoriesRequest(spinnerCategory, position);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSubCat.setAdapter(new AdapterSubCategory(getApplicationContext(), listCategories.get(position).getSubCat()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (spinnerCategory.getAdapter().getCount() == 0 && spinnerSubCat.getAdapter().getCount() == 0) {
                    showMessage("Nothing to display");
                } else {
                    String categoryName = ((Categories) spinnerCategory.getSelectedItem()).getName();
                    List<SubCategories> lis = new ArrayList<>();
                    lis.add((SubCategories) spinnerSubCat.getSelectedItem());
                    Categories category = new Categories(categoryName, lis);
                    if (position != -1) {
                        listCategory.set(position, category);
                        dialog.dismiss();
                    } else {
                        listCategory.add(category);
                        dialog.dismiss();
                    }
                    expandeView(listViewCategories, adapterCategory);
                }
            }
        }).show();
    }

    private void getCategoriesRequest(final Spinner spinner, final int position) {
        service.getAllCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                spinner.setAdapter(new AdapterCategories(getApplicationContext(), listCategories = response.body()));
                if (position != -1) {
                    Categories category = listCategory.get(position);
                    spinner.setSelection(((AdapterCategories) spinner.getAdapter()).getItemPosition(category));
                }
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void openImages(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Добавить изображение");
        builder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_image_dialog, null);
        builder.setView(dialogView);

        final EditText logoView = (EditText) dialogView.findViewById(R.id.logo);
        final TextView textLogoView = (TextView) dialogView.findViewById(R.id.textLogo);
        final Switch switchView = (Switch) dialogView.findViewById(R.id.switchView);

        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textLogoView.setText("Logo");
                } else {
                    textLogoView.setText("Picture");
                }
            }
        });
        if (position != -1) {
            Images image = listImage.get(position);
            switchView.setChecked(image.isLogo());
            logoView.setText(image.getImagePath());
        }
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = logoView.getText().toString();
                if (input.isEmpty()) {
                    showMessage("No empty field is allowed");
                } else {
                    Images image = new Images(input, switchView.isChecked());
                    if (position != -1) {
                        listImage.set(position, image);
                        dialog.dismiss();
                    } else {
                        listImage.add(image);
                        dialog.dismiss();
                    }
                    expandeView(listViewImages, adapterImage);
                }
            }
        }).show();
    }

    private void expandeView(ListView listView, ArrayAdapter adapter) {

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        adapter.notifyDataSetChanged();
    }
}