#!/bin/bash

etcdctl put "/datasource.url" "jdbc:postgresql://localhost:5432/my-crm"
etcdctl put "/datasource.username" "postgres"
etcdctl put "/datasource.password" "postgres"