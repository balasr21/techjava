{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "marvelinfo.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "marvelinfo.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "marvelinfo.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "marvelinfo.labels" -}}
helm.sh/chart: {{ include "marvelinfo.chart" . }}
{{ include "marvelinfo.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/*
Selector labels
*/}}
{{- define "marvelinfo.selectorLabels" -}}
app.kubernetes.io/name: {{ include "marvelinfo.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/*
Create the name of the service account to use
*/}}
{{- define "marvelinfo.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
    {{ default (include "marvelinfo.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
    {{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}


{{/*
Built full docker image name, including sha reference or tag
*/}}
{{- define "docker-image" -}}
{{- if hasKey .Values "dockerSha" -}}
  {{- printf "%s@%s" .Values.dockerImage .Values.dockerSha | quote -}}
{{- else if hasKey .Values "dockerTag" -}}
  {{- printf "%s:%s" .Values.dockerImage .Values.dockerTag | quote -}}
{{- else -}}
  {{- printf "%s" .Values.dockerImage | quote -}}
{{- end -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
*/}}
{{- define "mysql.fullname" -}}
{{- printf "%s-%s" .Release.Name "mysql" | trunc 63 | trimSuffix "-" -}}
{{- end -}}