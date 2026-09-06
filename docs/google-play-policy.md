# Google Play Policy for Identity (Current Stage)

This document is the working Google Play policy draft for the current version of the Identity app. It is intended to reflect the app as it exists today and should be reviewed and updated before any public release or major feature change.

Status: Early-stage / learning app prototype
Last updated: 2026-09-06

## 1. App overview

Identity is an Android app built with Kotlin and Jetpack Compose. At this stage, the app is focused on authentication and app navigation rather than large-scale user generation or commercial functionality.

Current implementation includes:
- Sign-up/sign-in flow using Clerk authentication
- Internet access for authentication and app communication
- Basic app screens such as loading, auth, home, profile, error, and usage views
- No in-app purchases, ads, or paid features
- No social networking, messaging, media upload, or content sharing features

## 2. Current policy position for Google Play

The app is currently treated as a low-risk utility/identity app with the following conditions:
- It does not collect sensitive data beyond what is required for authentication
- It does not contain restricted, harmful, or abusive content
- It does not include paid subscriptions, gambling, or regulated product categories
- It does not currently use sensitive device permissions such as camera, microphone, contacts, SMS, call logs, or location
- It does not include user-generated content feeds or community features

## 3. Data handled by the app

At this stage, the app may interact with the following data through third-party services:
- Account identity data such as email address and authentication status through Clerk
- Session and signed-in state information
- Basic app usage state required for navigation and account-aware behavior
- Networking data needed for backend/API access and auth validation

Important: the app does not currently have its own custom user database or storage system configured in the repository. Any real user data handling is dependent on the backend/auth provider used in production.

## 4. Privacy and data protection

The app should be treated as follows until a full production privacy policy and data handling review are completed:
- No personal data should be collected beyond what is necessary for authentication and app operation
- User data should only be shared with the authentication provider and any approved backend services
- All data requests, retention, and deletion flows should be documented in the final privacy policy before launch
- A publicly accessible privacy policy URL should be added before publishing to Google Play
- The app must provide a clear mechanism for users to request account deletion and data removal if production auth/backend services are enabled

## 5. Security requirements

The app must maintain the following standards before Google Play submission:
- Use secure authentication flows only through vetted providers
- Keep API keys and sensitive credentials out of public source repositories
- Use secure backend environments for production credentials
- Validate all externally loaded content and remote API interactions
- Avoid crash or log output that includes personally identifiable user data

## 6. User safety and compliance

This app should not be distributed as a product that includes:
- Illegal activity support or instructions
- Harmful, threatening, or abusive content
- Deceptive or misleading functionality
- Fake engagement, click fraud, or manipulation practices
- Unclear consent or data handling practices

## 7. Content and policy restrictions

The app currently does not include content that would normally trigger Google Play restrictions, such as:
- Adult content
- Gambling or regulated financial products
- Illegal goods or services
- Political persuasion or manipulation systems
- Malware or exploitative behavior

If the app later adds features such as chat, social content, payments, or user-generated media, this policy must be updated before release.

## 8. Permissions used by the app

Current Android manifest permissions:
- Internet access only

This is a low-risk permission profile for the current stage. If future features require location, camera, microphone, contact access, or storage permissions, a separate risk and compliance review will be required.

## 9. Required Google Play prep before launch

Before submission to Google Play, the app should have:
- A final app name and description
- Privacy Policy URL
- Data safety form completed
- App content rating reviewed
- Terms and conditions if needed
- Proper signing configuration and release build
- A final review of permissions and SDK usage
- Crash reporting and user support contact information

## 10. Future update rule

This policy should be updated whenever the app changes in one of the following ways:
- Adds a new login or identity provider
- Starts collecting user data beyond authentication basics
- Introduces monetization, ads, or subscriptions
- Adds camera, microphone, location, contacts or storage permissions
- Enables user-generated content, chat, social features, or uploads
- Uses a backend database or analytics provider
- Starts storing personal data locally or remotely

## 11. Version history

- v1.0 (2026-09-06): Initial policy draft for early-stage Identity app. Covers current authentication-first implementation, low-risk permissions, and Google Play readiness notes.

## 12. Legal note

This document is a working compliance draft and is not legal advice. For formal Google Play publication, verify all requirements against the latest Google Play policy and consult a legal or compliance professional when necessary.
