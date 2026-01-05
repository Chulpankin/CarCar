# CarCar

Android приложение для поиска и управления автомобилями.

## Структура проекта

Проект использует модульную архитектуру с разделением на:
- **app** - главный модуль приложения
- **core** - базовые модули (common, data, ui)
- **features** - функциональные модули (signin, signup, carlist, carsearch, favorites)

## Тестирование

### Покрытие тестами Use-Case

Проект содержит юнит-тесты для всех Use-Case в следующих модулях:

#### ✅ SignIn Feature
- `SignInUseCaseImpl` - тестирует вход пользователя
- `IsUserAuthorizedUseCaseImpl` - тестирует проверку авторизации пользователя

#### ✅ SignUp Feature
- `SignUpUseCaseImpl` - тестирует регистрацию пользователя

#### ✅ Favorites Feature
- `GetFavoriteCarsUseCaseImpl` - тестирует получение списка избранных автомобилей
- `IsFavoriteUseCaseImpl` - тестирует проверку, является ли автомобиль избранным
- `ToggleFavoriteUseCaseImpl` - тестирует добавление/удаление автомобиля из избранного

#### ✅ CarList Feature
- `GetCarsUseCaseImpl` - тестирует получение списка автомобилей с фильтрацией

#### ✅ Core/Data Network Use-Cases
- `GetCarMakesUseCaseImpl` - тестирует получение списка производителей
- `GetCarModelsUseCaseImpl` - тестирует получение списка моделей
- `GetCarTrimsUseCaseImpl` - тестирует получение списка комплектаций
- `GetCarModelDetailsUseCaseImpl` - тестирует получение деталей модели
- `GetCarYearsUseCaseImpl` - тестирует получение списка годов выпуска

### Запуск тестов

Для запуска всех юнит-тестов:
```bash
./gradlew test
```

### CI/CD

Тесты автоматически запускаются в CI/CD pipeline при создании Pull Request:
- Detekt (статический анализ кода)
- Unit Tests (все юнит-тесты)
- Build & Firebase App Distribution

> **Примечание:** Это тестовый PR для проверки CI/CD pipeline.

## Настройка Firebase

### Получение SHA-1 и SHA-256 fingerprint

Для работы Firebase Authentication необходимо добавить SHA-1 и SHA-256 fingerprint в Firebase Console.

#### Для debug-версии:
```bash
cd android
./gradlew signingReport
```

Или вручную:
```bash
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
```

#### Для release-версии:
```bash
keytool -list -v -keystore <path-to-your-keystore> -alias <your-key-alias>
```

### Добавление fingerprint в Firebase Console

1. Откройте [Firebase Console](https://console.firebase.google.com/)
2. Выберите проект `carcar-44eb6`
3. Перейдите в **Project Settings** (⚙️) → **Your apps**
4. Выберите Android приложение
5. Нажмите **Add fingerprint**
6. Добавьте SHA-1 и SHA-256 из вывода команды выше
7. Скачайте обновленный `google-services.json` и замените файл в `app/google-services.json`

### Настройка Firebase Authentication

1. В Firebase Console перейдите в **Authentication** → **Sign-in method**
2. Убедитесь, что **Email/Password** включен
3. Если используется reCAPTCHA, убедитесь, что он правильно настроен

### Решение ошибки CONFIGURATION_NOT_FOUND

Если вы получаете ошибку `CONFIGURATION_NOT_FOUND` при регистрации:
- Убедитесь, что SHA-1/SHA-256 добавлены в Firebase Console
- Проверьте, что `google-services.json` актуален
- Убедитесь, что Firebase Authentication включен в консоли
- Пересоберите проект после обновления `google-services.json`

## Технологии

- Kotlin
- Jetpack Compose
- Dagger Hilt (Dependency Injection)
- Firebase (Analytics, Crashlytics, Performance, Auth, Firestore)
- Paging 3
- Ktor (Network)
- MockK (Testing)
- Detekt (Code Analysis)

