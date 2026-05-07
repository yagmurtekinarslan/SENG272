package model;

public class SessionData {
    private UserProfile profile;
    private QualityType selectedQualityType;
    private Mode selectedMode;
    private Scenario selectedScenario;

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public QualityType getSelectedQualityType() {
        return selectedQualityType;
    }

    public void setSelectedQualityType(QualityType selectedQualityType) {
        this.selectedQualityType = selectedQualityType;
    }

    public Mode getSelectedMode() {
        return selectedMode;
    }

    public void setSelectedMode(Mode selectedMode) {
        this.selectedMode = selectedMode;
    }

    public Scenario getSelectedScenario() {
        return selectedScenario;
    }

    public void setSelectedScenario(Scenario selectedScenario) {
        this.selectedScenario = selectedScenario;
    }

    public boolean isProfileComplete() {
        return profile != null && profile.isValid();
    }

    public boolean isDefinitionComplete() {
        return selectedQualityType != null
                && selectedMode != null
                && selectedScenario != null;
    }

    public void clear() {
        profile = null;
        selectedQualityType = null;
        selectedMode = null;
        selectedScenario = null;
    }
}
