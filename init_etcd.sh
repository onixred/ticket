#!/bin/bash

etcdctl put "/datasource.url" "jdbc:postgresql://localhost:5432/my-crm"
etcdctl put "/datasource.username" "postgres"
etcdctl put "/datasource.password" "postgres"

etcdctl put "/kafka.bootstrap-servers" "localhost:9092"
etcdctl put "/kafka.topic.name" "ticket_topic"
etcdctl put "/kafka.consumer.group-id" "ticket_group"