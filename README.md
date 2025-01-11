# 📱 Story App V2

<div align="center">

[![Kotlin](https://img.shields.io/badge/Kotlin-1.8.0-blue.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2023.1.1-green.svg?style=for-the-badge&logo=android-studio)](https://developer.android.com/studio)
[![Minimum SDK](https://img.shields.io/badge/Minimum%20SDK-26-orange.svg?style=for-the-badge&logo=android)](https://developer.android.com/studio/releases/platforms)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg?style=for-the-badge&logo=github)](https://github.com/jihanfebri/StoryApp-V2)

### 🌟 Share Your Stories, Connect with Others 🌟

</div>

## 📖 About The Project

Story App is a modern social media platform that allows users to share their memorable moments through stories. Built with the latest Android development practices and Material Design principles, this app provides an immersive and intuitive experience for sharing and discovering stories from around the world.

### 🎯 Core Features

- **Authentication System**
  - 🔐 Secure login and registration
  - 🔄 Session management
  - 👤 User profile customization

- **Story Management**
  - 📸 Create stories with images
  - 📝 Add descriptions and locations
  - 🗺️ View stories on interactive map
  - 🔍 Story details with zoom capability

- **User Experience**
  - 🎨 Material Design 3 implementation
  - ✨ Smooth animations & transitions
  - 📱 Responsive layout design
  - 🔄 Pull-to-refresh functionality

- **Technical Features**
  - 📊 Efficient pagination
  - 📍 Location services integration
  - 💾 Local data caching
  - 🔄 Background sync

## 🛠️ Built With

### Architecture
```
📦 MVVM (Model-View-ViewModel)
 ┣ 📂 Model (Data Layer)
 ┃ ┣ 📂 Remote (API Services)
 ┃ ┣ 📂 Local (Room Database)
 ┃ ┗ 📂 Repository
 ┣ 📂 View (UI Layer)
 ┃ ┣ 📂 Activities
 ┃ ┣ 📂 Fragments
 ┃ ┗ 📂 Adapters
 ┗ 📂 ViewModel (Business Logic)
```

### Tech Stack
- **🏗️ Architecture Components**
  - ViewModel
  - LiveData
  - DataBinding
  - Room
  - Navigation

- **🌐 Networking**
  - Retrofit2
  - OkHttp3
  - GSON

- **📸 Image Processing**
  - Glide
  - CameraX

- **🗺️ Maps & Location**
  - Google Maps SDK
  - Location Services

- **🎨 UI Components**
  - Material Design 3
  - ConstraintLayout
  - RecyclerView
  - ViewPager2

## 📲 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 8 or later
- Android SDK API 26 or later
- Google Maps API Key

### Installation

1. Clone the repository
```bash
git clone https://github.com/jihanfebri/StoryApp-V2.git
```

2. Open project in Android Studio

3. Add your Google Maps API key in `local.properties`:
```properties
MAPS_API_KEY=your_api_key_here
```

4. Sync project with Gradle files

5. Run on emulator or physical device

## 🌐 API Integration

The app integrates with Dicoding Story API v1:
- Base URL: `https://story-api.dicoding.dev/v1/`
- Authentication: Bearer Token
- Supports: Image upload, pagination, location data

## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Jihan Febriharvianti Wirawan**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/jihanfebriharvianti/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black.svg?style=for-the-badge&logo=github)](https://github.com/jihanfebri)

## 🙏 Acknowledgments

- [Dicoding Indonesia](https://www.dicoding.com/)
- [Android Jetpack Libraries](https://developer.android.com/jetpack)
- [Material Design](https://material.io/design)
- [Google Maps Platform](https://cloud.google.com/maps-platform)

---

<div align="center">
  <sub>Built with ❤️ by Jihan Febriharvianti Wirawan</sub>
  <br>
  <sub>© 2024 Story App. All rights reserved.</sub>
</div>
