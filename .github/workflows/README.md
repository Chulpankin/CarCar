# CI/CD Configuration

## Настройка секретов в GitHub

Для работы CI/CD необходимо добавить следующие секреты в настройках репозитория (Settings → Secrets and variables → Actions):

1. **FIREBASE_APP_ID** - ID приложения в Firebase Console
   - Найти в Firebase Console → Project Settings → Your apps → App ID

2. **FIREBASE_TOKEN** - Токен для Firebase CLI
   - Сгенерировать командой: `firebase login:ci`
   - Или через Firebase Console → Project Settings → Service Accounts → Generate new private key

3. **GOOGLE_SERVICES_JSON** (опционально) - Содержимое файла `google-services.json`
   - Если файл уже в репозитории, этот секрет не нужен
   - Если файл в .gitignore, добавьте его содержимое как секрет

## Настройка Firebase App Distribution

1. Установите Firebase CLI:
   ```bash
   npm install -g firebase-tools
   ```

2. Войдите в Firebase:
   ```bash
   firebase login:ci
   ```

3. Скопируйте полученный токен и добавьте его как секрет `FIREBASE_TOKEN` в GitHub

4. Создайте группу тестировщиков в Firebase Console:
   - Firebase Console → App Distribution → Testers & Groups
   - Создайте группу "testers" или используйте существующую

5. Найдите App ID в Firebase Console и добавьте его как секрет `FIREBASE_APP_ID`

## Workflow

Workflow запускается автоматически на каждый Pull Request и выполняет:

1. **Detekt** - статический анализ кода Kotlin
2. **Unit Tests** - запуск всех юнит-тестов
3. **Build & Distribute** - сборка APK и загрузка в Firebase App Distribution

Все шаги должны пройти успешно, иначе PR не будет одобрен.

