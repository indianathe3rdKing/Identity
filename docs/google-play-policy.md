# Google Play Policy for Identity (Current Stage)

This document is the working Google Play policy draft for the current version of the Identity app. It is intended to reflect the app as it exists today and should be reviewed and updated before any public release or major feature change.

Status: Early-stage / learning app prototype
Last updated: 2026-09-06

## 1. App overview

Identity is an Android app built with Kotlin and Jetpack Compose. At this stage, it is an early-stage learning and prototype app rather than a production consumer product or revenue-generating service.

Current implementation includes:
- Basic app screens such as loading, home, profile, error, and usage views
- Lightweight UI navigation and app flow structure
- Internet access for future app communication and backend integration
- No in-app purchases, ads, or paid features
- No social networking, messaging, media upload, or content sharing features
- No active authentication provider, user account system, or personal-data collection configured at this stage

## 2. Current policy position for Google Play

The app is currently treated as a low-risk utility and prototype app with the following conditions:
- It does not currently collect personal user data in the app itself
- It does not contain restricted, harmful, or abusive content
- It does not include paid subscriptions, gambling, or regulated product categories
- It does not currently use sensitive device permissions such as camera, microphone, contacts, SMS, call logs, or location
- It does not include user-generated content feeds or community features

## 3. Data handled by the app

At this stage, the app does not have a production authentication system, user database, or backend storage configured. The app is therefore treated as a non-personal-data app while it remains in early development.

This means:
- No user accounts are created in the current app build
- No custom user data is stored by the app itself
- No personal information is intentionally collected at this stage
- Any future backend or authentication integration will require a fresh privacy review before launch

## 4. Privacy and data protection

The app should be treated as follows until a full production privacy policy and data-handling review are completed:
- No personal data should be collected beyond what is necessary for app operation and testing
- No user account data should be stored unless a backend and privacy review are completed
- All future data requests, retention, and deletion flows should be documented in the final privacy policy before launch
- A publicly accessible privacy policy URL should be added before publishing to Google Play
- The app must provide a clear mechanism for users to request account deletion and data removal if production auth or backend services are introduced later

## 5. Security requirements

The app must maintain the following standards before Google Play submission:
- Do not add or store user data without a clear privacy and security review
- Keep any future API keys and sensitive credentials out of public source repositories
- Use secure backend environments for production credentials if authentication or storage is introduced later
- Validate all externally loaded content and remote API interactions
- Avoid crash or log output that includes personally identifiable user data

## 6. User safety and compliance

This app should not be distributed as a product that includes:
- Illegal activity support or instructions
- Harmful, threatening, or abusive content
- Deceptive or misleading functionality
- Fake engagement, click fraud, or manipulation practices
- Unclear consent or data-handling practices

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
- A privacy policy URL
- A completed Data Safety form
- App content rating reviewed
- Terms and conditions if needed
- Proper signing configuration and release build
- A final review of permissions and SDK usage
- Crash reporting and user support contact information

## 10. Future update rule

This policy should be updated whenever the app changes in one of the following ways:
- Adds a login, identity provider, or account system
- Starts collecting user data of any kind
- Introduces monetization, ads, or subscriptions
- Adds camera, microphone, location, contacts, or storage permissions
- Enables user-generated content, chat, social features, or uploads
- Uses a backend database or analytics provider
- Starts storing personal data locally or remotely

## 11. Version history

- v1.1 (2026-09-06): Updated to reflect the current early-stage app position: no user data is being collected or stored because no auth/backend provider is active in the project yet.
- v1.0 (2026-09-06): Initial policy draft for early-stage Identity app. Covers current low-risk permissions and onboarding considerations for future Google Play readiness.

## 12. Legal note

This document is a working compliance draft and is not legal advice. For formal Google Play publication, verify all requirements against the latest Google Play policy and consult a legal or compliance professional when necessary.
