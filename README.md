# Story App 📱

## Overview
Story App is a modern Android application that allows users to share and discover stories through an interactive platform. Built with the latest Android development practices and libraries, this app provides a seamless experience for users to share their moments with the community.

## Features ✨

- **User Authentication** 
  - Secure login and registration system
  - Session management
  - User profile management

- **Story Sharing**
  - Create and share stories with images
  - Add descriptions to your stories
  - View stories from other users
  - Location-based story mapping

- **Interactive UI**
  - Modern Material Design implementation
  - Smooth animations using Lottie
  - Responsive layout for different screen sizes
  - Pull-to-refresh functionality

- **Advanced Features**
  - Pagination support for efficient data loading
  - Location integration with Google Maps
  - Image handling with Glide
  - Offline capability with data caching

## Tech Stack 🛠️

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Jetpack Components:**
  - ViewModel
  - LiveData
  - DataStore
  - ViewBinding
  - Coroutines
  - Paging 3

- **Networking:**
  - Retrofit2
  - OkHttp3
  - GSON

- **Image Loading:** Glide
- **Maps:** Google Maps SDK
- **Animation:** Lottie
- **Logging:** Timber
- **UI Components:**
  - Material Design
  - CircleImageView
  - SwipeRefreshLayout

## Requirements 📋

- Android Studio Arctic Fox or later
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.8+
- Google Maps API Key (for location features)

## Installation 🚀

1. Clone this repository
```bash
git clone [repository-url]
```

2. Open the project in Android Studio

3. Add your Google Maps API key in the local.properties file:
```properties
MAPS_API_KEY=your_api_key_here
```

4. Sync the project with Gradle files

5. Run the app on an emulator or physical device

## Architecture 🏗️

This app follows the MVVM (Model-View-ViewModel) architecture pattern and clean architecture principles:

```
app/
├── data/
│   ├── remote/
│   ├── local/
│   └── repository/
├── di/
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── auth/
│   ├── story/
│   └── maps/
└── utils/
```

## API Integration 🌐

The app integrates with the Dicoding Story API (v1) for all backend operations. Base URL: `https://story-api.dicoding.dev/v1/`

## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request.

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments 🙏

- Thanks to Dicoding for providing the API
- All contributors who have helped this project
- Open source libraries used in this project

---

Made with ❤️ by Jihan Febriharvianti Wirawan
