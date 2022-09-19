<h1>E Commerece app</h1>
<p>\The app showcase clothings with details, user can add items in cart and checkout</p>
<hr>
<h3>Used Technologies</h3>
<ul>
  <li>Kotlin</li>
  <li>Room Database</li>
  <li>retrofit 2</li>
  <li>Dagger Hilt injection</li>
  <li>Data binding and Perciable</li>
  <li>Recycler View</li>
</ul>  
<hr>
<h3>Implimentations of Dependencies in project</h3>

def jetpack_version = "2.1.0"

    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //legacy support
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

    // hilt
    implementation "com.google.dagger:hilt-android:2.41"
    kapt "com.google.dagger:hilt-compiler:2.41"

    // networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.code.gson:gson:2.8.9'
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"

    // Image Loading
    implementation "io.coil-kt:coil:1.4.0"

    // Coroutine and Lifecycle
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    implementation "androidx.fragment:fragment-ktx:1.5.2"

    //Room
    implementation "androidx.room:room-ktx:$jetpack_version"
    kapt "androidx.room:room-compiler:$jetpack_version"
    kapt "com.android.databinding:compiler:$jetpack_version"

    //dimens
    implementation 'com.intuit.sdp:sdp-android:1.0.6'
    //lottie
    implementation 'com.airbnb.android:lottie:3.0.6'
    //email
    implementation 'com.sun.mail:android-mail:1.6.0'
    implementation 'com.sun.mail:android-activation:1.6.0'
